package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.common.*;
import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.CreateProjectVo;
import com.informanage.usermain.entity.VO.ProjectQueryVo;
import com.informanage.usermain.entity.VO.WorkInfoQuery;
import com.informanage.usermain.service.ProjectMenberlInfoService;
import com.informanage.usermain.service.ProjectService;
import com.informanage.usermain.service.UserWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 项目记录信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/usermain/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    private UserWorkInfoController workInfoController;
    @Autowired
    private UserWorkInfoService workInfoService;

    @PostMapping("/creatProject")
    public R creatProject(@RequestBody CreateProjectVo vo, HttpServletRequest request) {
        String name = vo.getName();
        String info = vo.getInfo();
        List yesData = vo.getYesData();
        if (StringUtils.isEmpty(name))
            return R.error().message("请填写项目名称");
        if (StringUtils.isEmpty(info))
            return R.error().message("请填写项目详情");
        if (yesData.size() == 0)
            return R.error().message("请添加项目成员");

        boolean getPower = workInfoController.isGetPower(request, 12);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }

        JwtInfo user = JwtUtils.getMemberIdByJwtToken(request);
        UserWorkInfo byId = workInfoService.getById(user.getId());
        Project project = new Project().setCreatuserid(byId.getUserid())
                .setCreatusername(byId.getName())
                .setInfo(info)
                .setMembernum(yesData.size())
                .setName(name)
                .setStatis("wait");
        //下面将成员信息添加到项目成员表中
        projectService.createProject(yesData, project);
        return R.ok().message("项目创建成功！");
    }

    @PostMapping("/getAllProject/{current}/{size}")
    public R getAllProject(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                           @RequestBody(required = false) ProjectQueryVo projectQueryVo) {
        boolean getPower = workInfoController.isGetPower(request, 5);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        String name = projectQueryVo.getName();
        String creatusername = projectQueryVo.getCreatusername();
        String statis = projectQueryVo.getStatis();
        Integer membernumS = projectQueryVo.getMembernumS();
        Integer membernumE = projectQueryVo.getMembernumE();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(creatusername)) {
            queryWrapper.like("creatusername", creatusername);
        }
        if (!StringUtils.isEmpty(statis)) {
            queryWrapper.eq("statis", statis);
        }
        if (!StringUtils.isEmpty(membernumS)) {
            queryWrapper.gt("membernum", membernumS);
        }
        if (!StringUtils.isEmpty(membernumE)) {
            queryWrapper.lt("membernum", membernumE);
        }

        Page<Project> projectPage = new Page<>(current, size);
        projectService.page(projectPage, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", projectPage.getTotal());
        map.put("list", projectPage.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    @Autowired
    ProjectMenberlInfoService service;

    /**
     * 自动检测是否完成项目
     */
    @Scheduled(cron = "0 0/30 * * * ? ")//每半小时执行一次
    private void taskCheckIsPass() {
        List<Project> list = projectService.list();
        boolean is = true;
        for (Project project : list) {
            if (project.getStatis().equals("wait")) {//项目还未完成，就查询员工完成状态
                QueryWrapper<ProjectMenberlInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("projectid", project.getId());
                queryWrapper.select("statis");
                List<ProjectMenberlInfo> list1 = service.list(queryWrapper);
                for (ProjectMenberlInfo p : list1) {
                    if (p.getStatis().equals("wait")) {
                        is = false;
                    }
                }
                if (is) {
                    projectService.updateById(project.setStatis("pass"));
                }
            }
        }
    }
}

