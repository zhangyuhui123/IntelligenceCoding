package top.hcode.hoj.remoteJudge;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import top.hcode.hoj.pojo.entity.judge.Judge;
import top.hcode.hoj.remoteJudge.entity.RemoteJudgeDTO;
import top.hcode.hoj.remoteJudge.task.RemoteJudgeStrategy;
import top.hcode.hoj.service.impl.JudgeServiceImpl;
import top.hcode.hoj.service.impl.RemoteJudgeServiceImpl;
import top.hcode.hoj.util.Constants;

@Component
@Slf4j(topic = "hoj")
@RefreshScope
public class RemoteJudgeToSubmit {

    @Autowired
    private JudgeServiceImpl judgeService;

    @Autowired
    private RemoteJudgeServiceImpl remoteJudgeService;


    @Value("${hoj-judge-server.name}")
    private String name;

    public boolean process(RemoteJudgeStrategy remoteJudgeStrategy) {

        RemoteJudgeDTO remoteJudgeDTO = remoteJudgeStrategy.getRemoteJudgeDTO();
        log.info("Ready Send Task to RemoteJudgeDTO => {}", remoteJudgeDTO);

        String errLog = null;
        try {
            remoteJudgeStrategy.submit();
        } catch (Exception e) {
            log.error("Submit Failed! Error:", e);
            errLog = e.getMessage();
        }

        Long submitId = remoteJudgeDTO.getSubmitId();
        // 提交失败 前端手动按按钮再次提交 修改状态 STATUS_SUBMITTED_FAILED
        if (submitId == null ||submitId == -1L) {
            // 将使用的账号放回对应列表
            log.error("[{}] Submit Failed! Begin to return the account to other task!", remoteJudgeDTO.getOj());
            remoteJudgeService.changeAccountStatus(remoteJudgeDTO.getOj(),
                    remoteJudgeDTO.getUsername());
            if (remoteJudgeDTO.getOj().equals(Constants.RemoteJudge.GYM_JUDGE.getName())
                    || remoteJudgeDTO.getOj().equals(Constants.RemoteJudge.CF_JUDGE.getName())) {
                // 对CF特殊，归还账号及判题机权限
                log.error("[{}] Submit Failed! Begin to return the Server Status to other task!", remoteJudgeDTO.getOj());
                remoteJudgeService.changeServerSubmitCFStatus(remoteJudgeDTO.getServerIp(), remoteJudgeDTO.getServerPort());
            }

            // 更新此次提交状态为提交失败！
            UpdateWrapper<Judge> judgeUpdateWrapper = new UpdateWrapper<>();
            judgeUpdateWrapper.set("status", Constants.Judge.STATUS_SUBMITTED_FAILED.getStatus())
                    .set("error_message", errLog)
                    .eq("submit_id", remoteJudgeDTO.getJudgeId());
            judgeService.update(judgeUpdateWrapper);
            // 更新其它表
            judgeService.updateOtherTable(remoteJudgeDTO.getSubmitId(),
                    Constants.Judge.STATUS_SYSTEM_ERROR.getStatus(),
                    remoteJudgeDTO.getCid(),
                    remoteJudgeDTO.getUid(),
                    remoteJudgeDTO.getPid(),
                    null,
                    null);
            return false;
        }

        // 提交成功顺便更新状态为-->STATUS_PENDING 判题中...
        judgeService.updateById(new Judge()
                .setSubmitId(remoteJudgeDTO.getJudgeId())
                .setStatus(Constants.Judge.STATUS_PENDING.getStatus())
                .setVjudgeSubmitId(submitId)
                .setVjudgeUsername(remoteJudgeDTO.getUsername())
                .setVjudgePassword(remoteJudgeDTO.getPassword())
                .setJudger(name)
        );

        log.info("[{}] Submit Successfully! The submit_id of remote judge is [{}]. Waiting the result of the task!",
                submitId, remoteJudgeDTO.getOj());
        return true;
    }
}
