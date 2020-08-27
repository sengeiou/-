package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.common.*;
import com.informanage.usermain.entity.Project;
import com.informanage.usermain.entity.ProjectMenberlInfo;
import com.informanage.usermain.entity.VO.AllProjectDetailVo;
import com.informanage.usermain.entity.VO.MyProjectVo;
import com.informanage.usermain.entity.VO.ProjectMemberDetailQueryVo;
import com.informanage.usermain.service.ProjectMenberlInfoService;
import com.informanage.usermain.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 项目成员详细信息 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/usermain/project-menberl-info")
@CrossOrigin
public class ProjectMenberlInfoController {
@Autowired
    ProjectMenberlInfoService service;
    @Autowired
    private UserWorkInfoController workInfoController;
    @Autowired
    ProjectService projectService;

    @PostMapping("/getMyWaitProject/{current}/{size}")
    public R getAllProject(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                           @RequestBody(required = false) ProjectMemberDetailQueryVo projectMemberDetailVo) {
        JwtInfo memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<MyProjectVo> queryWrapper = new QueryWrapper<>();
        String name = projectMemberDetailVo.getName();
        String creatusername = projectMemberDetailVo.getCreatusername();
        String start = projectMemberDetailVo.getTimeStart();
        String end = projectMemberDetailVo.getTimeEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("p.name", name);
        }
        if(!StringUtils.isEmpty(creatusername)){
            queryWrapper.like("p.creatusername", creatusername);
        }
        if(!StringUtils.isEmpty(start)){
            queryWrapper.gt("m.gmt_create", start);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.lt("m.gmt_create", end);
        }
        queryWrapper.eq("m.memberid",memberIdByJwtToken.getId());
        queryWrapper.eq("m.statis", "wait");
        Page<MyProjectVo> projectPage = new Page<>(current, size);
        List<MyProjectVo> records=service.selectMyPage(projectPage,queryWrapper);
        projectPage.setRecords(records);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", projectPage.getTotal());
        map.put("list", projectPage.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    @PostMapping("/getMyPassProject/{current}/{size}")
    public R getMyPassProject(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                           @RequestBody(required = false) ProjectMemberDetailQueryVo projectMemberDetailVo) {
        JwtInfo memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<MyProjectVo> queryWrapper = new QueryWrapper<>();
        String name = projectMemberDetailVo.getName();
        String creatusername = projectMemberDetailVo.getCreatusername();
        String start = projectMemberDetailVo.getTimeStart();
        String end = projectMemberDetailVo.getTimeEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("p.name", name);
        }
        if(!StringUtils.isEmpty(creatusername)){
            queryWrapper.like("p.creatusername", creatusername);
        }
        if(!StringUtils.isEmpty(start)){
            queryWrapper.gt("m.gmt_create", start);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.lt("m.gmt_create", end);
        }
        queryWrapper.eq("m.memberid",memberIdByJwtToken.getId());
        queryWrapper.eq("m.statis", "pass");
        Page<MyProjectVo> projectPage = new Page<>(current, size);
        List<MyProjectVo> records=service.selectMyPage(projectPage,queryWrapper);
        projectPage.setRecords(records);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", projectPage.getTotal());
        map.put("list", projectPage.getRecords());
        return R.ok().data(map);
    }

    /**
     * 个人项目完成提交接口
     * @param id
     * @return
     */
    @GetMapping("/pass/{id}")
    public R pass(@PathVariable String id){
        UpdateWrapper<ProjectMenberlInfo> wrapper=new UpdateWrapper<>();
        wrapper.eq("id", id);
        wrapper.set("statis", "pass");
        service.update(wrapper);

        //这里不做查询项目整体完成状态，全部完成则更改项目状态
        //改为定时查询未完成项目的整体完成状态，全部完成则更新，提高用户体验
        return R.ok().message("提交成功！");
    }

    /**
     * 根据部门ID查询员工完成状态等信息
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/checkProjectById/{id}")
    public R checkProjectById(@PathVariable String id,HttpServletRequest request){
        boolean getPower = workInfoController.isGetPower(request, 5);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<ProjectMenberlInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("projectid", id);
//        queryWrapper.select("statis");
//        queryWrapper.select("membername");//只能查询出单列来
        List<ProjectMenberlInfo> list = service.list(queryWrapper);
        return R.ok().data("list",list);
    }

    /**
     * 根据部门ID删除全部相关记录
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/delById/{id}")
    public R delById(@PathVariable String id,HttpServletRequest request){
        boolean getPower = workInfoController.isGetPower(request, 5);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<ProjectMenberlInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("projectid", id);
        service.remove(queryWrapper);
        projectService.removeById(id);
        return R.ok().message("删除成功！");
    }

    /**
     * 根据员工ID删除记录
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/delMemberById/{id}")
    public R delMemberById(@PathVariable String id,HttpServletRequest request){
        boolean getPower = workInfoController.isGetPower(request, 10);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<ProjectMenberlInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.select("projectid");
        ProjectMenberlInfo one = service.getOne(queryWrapper);
        service.removeById(id);
        //
        //这里要更新人数
        Project byId = projectService.getById(one.getProjectid());
        byId.setMembernum(byId.getMembernum()-1);
        projectService.updateById(byId);

        return R.ok().message("删除成功！");
    }
    @PostMapping("/getAllProjectDetail/{current}/{size}")
    public R getAllProjectDetail(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                                 @RequestBody(required = false) ProjectMemberDetailQueryVo projectMemberDetailVo){
        boolean getPower = workInfoController.isGetPower(request, 10);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }

        QueryWrapper<AllProjectDetailVo> queryWrapper = new QueryWrapper<>();
        String name = projectMemberDetailVo.getName();
        String creatusername = projectMemberDetailVo.getCreatusername();
        String statis = projectMemberDetailVo.getStatis();
        String start = projectMemberDetailVo.getTimeStart();
        String end = projectMemberDetailVo.getTimeEnd();
        String membername = projectMemberDetailVo.getMembername();
        if(!StringUtils.isEmpty(membername)){
            queryWrapper.like("m.membername", membername);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("p.name", name);
        }
        if(!StringUtils.isEmpty(statis)){
            queryWrapper.eq("m.statis", statis);
        }
        if(!StringUtils.isEmpty(creatusername)){
            queryWrapper.like("p.creatusername", creatusername);
        }
        if(!StringUtils.isEmpty(start)){
            queryWrapper.gt("m.gmt_create", start);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.lt("m.gmt_create", end);
        }
        IPage<AllProjectDetailVo> page=service.selectAllDetailPage(current,size,queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return R.ok().data(map);//前端保存好id，后端修改状态时需要id
    }
}

