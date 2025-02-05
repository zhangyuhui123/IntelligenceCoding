package top.hcode.hoj.controller.admin;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hcode.hoj.common.result.CommonResult;
import top.hcode.hoj.service.judge.impl.RejudgeServiceImpl;


import javax.annotation.Resource;

/**
 * @Author: Himit_ZH
 * @Date: 2021/1/3 14:09
 * @Description: 超管重判提交
 */

@RestController
@RequestMapping("/api/admin/judge")

public class AdminJudgeController {

    @Resource
    private RejudgeServiceImpl rejudgeService;


    @GetMapping("/rejudge")
    @RequiresAuthentication
    @RequiresRoles("root")  // 只有超级管理员能操作
    @RequiresPermissions("rejudge")
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public CommonResult rejudge(@RequestParam("submitId") Long submitId) {
        return rejudgeService.rejudge(submitId);
    }

    @GetMapping("/rejudge-contest-problem")
    @RequiresAuthentication
    @RequiresRoles("root")  // 只有超级管理员能操作
    @RequiresPermissions("rejudge")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult rejudgeContestProblem(@RequestParam("cid") Long cid, @RequestParam("pid") Long pid) {
        return rejudgeService.rejudgeContestProblem(cid, pid);
    }
}
