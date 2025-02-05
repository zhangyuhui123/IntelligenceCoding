package top.hcode.hoj.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hcode.hoj.common.result.CommonResult;
import top.hcode.hoj.pojo.entity.common.Announcement;
import top.hcode.hoj.pojo.vo.AnnouncementVo;
import top.hcode.hoj.service.common.impl.AnnouncementServiceImpl;

import javax.validation.Valid;

/**
 * @Author: Himit_ZH
 * @Date: 2020/12/10 19:53
 * @Description:
 */
@RestController
@RequiresAuthentication
public class AnnouncementController {

    @Autowired
    private AnnouncementServiceImpl announcementDao;

    @GetMapping("/api/admin/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult getAnnouncementList(@RequestParam(value = "limit", required = false) Integer limit,
                                            @RequestParam(value = "currentPage", required = false) Integer currentPage){
        if (currentPage == null || currentPage < 1) currentPage = 1;
        if (limit == null || limit < 1) limit = 10;
        IPage<AnnouncementVo> announcementList = announcementDao.getAnnouncementList(limit, currentPage,false);
        if (announcementList.getTotal() == 0) { // 未查询到一条数据
            return CommonResult.successResponse(announcementList,"暂无数据");
        } else {
            return CommonResult.successResponse(announcementList, "获取成功");
        }
    }

    @DeleteMapping("/api/admin/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult deleteAnnouncement(@Valid @RequestParam("aid")long aid){
        boolean result = announcementDao.removeById(aid);
        if (result) { // 删除成功
            return CommonResult.successResponse(null,"删除成功！");
        } else {
            return CommonResult.errorResponse("删除失败！",CommonResult.STATUS_FAIL);
        }
    }

    @PostMapping("/api/admin/announcement")
    @RequiresRoles("root")  // 只有超级管理员能操作
    @RequiresPermissions("announcement_admin")
    public CommonResult addAnnouncement(@RequestBody Announcement announcement){
        boolean result = announcementDao.save(announcement);
        if (result) { // 添加成功
            return CommonResult.successResponse(null,"添加成功！");
        } else {
            return CommonResult.errorResponse("添加失败",CommonResult.STATUS_FAIL);
        }
    }

    @PutMapping("/api/admin/announcement")
    @RequiresPermissions("announcement_admin")
    public CommonResult updateAnnouncement(@RequestBody Announcement announcement){
        boolean result = announcementDao.saveOrUpdate(announcement);
        if (result) { // 更新成功
            return CommonResult.successResponse(null,"修改成功！");
        } else {
            return CommonResult.errorResponse("修改失败",CommonResult.STATUS_FAIL);
        }
    }
}