package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.common.*;
import com.informanage.usermain.entity.MApplyDepartment;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;
import com.informanage.usermain.entity.VO.WorkInfoQuery;
import com.informanage.usermain.service.MApplyDepartmentService;
import com.informanage.usermain.service.UserWorkInfoService;
import com.informanage.usermain.utils.RedisProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/usermain/user-work-info")
@CrossOrigin
public class UserWorkInfoController {
    @Autowired
    UserWorkInfoService userWorkInfoService;
    @Autowired
    MApplyDepartmentService mApplyDepartmentService;
    @Autowired
    RedisProduct redisProduct;//自定义类，将需要邮件通知的用户加入redis list集合中

    @GetMapping("/getWorkInfo")
    public R getWorkInfo(HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        UserWorkInfo userWorkInfo = userWorkInfoService.getById(jwtInfo.getId());
        return R.ok().data("user", userWorkInfo);
    }

    @GetMapping("/getNormalWorkInfoByUserId/{id}")
    public R getNormalWorkInfoByUserId(HttpServletRequest request, @PathVariable String id) {
        //调用方法判断权限
        boolean result = isGetPower(request, 8);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        UserWorkInfo userWorkInfo = userWorkInfoService.getById(id);
        return R.ok().data("user", userWorkInfo);
    }

    @GetMapping("/getGreatWorkInfoByUserId/{id}")
    public R getGreatWorkInfoByUserId(HttpServletRequest request, @PathVariable String id) {
        //调用方法判断权限
        boolean result = isGetPower(request, 9);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        UserWorkInfo userWorkInfo = userWorkInfoService.getById(id);
        return R.ok().data("user", userWorkInfo);
    }

    /**
     * 提交申请的方法，插入2张表。（工作信息修改状态和数据，申请记录表直接插入一条记录）
     *
     * @param userWorkInfo
     * @param request
     * @return
     */
    @PostMapping("/updateWorkInfo")
    public R updateWorkInfo(@RequestBody UserWorkInfo userWorkInfo, HttpServletRequest request) {
        Integer departmentid = userWorkInfo.getDepartmentid();
        String name = userWorkInfo.getName();
        String phone = userWorkInfo.getPhone();
        Integer salary = userWorkInfo.getSalary();
        String sex = userWorkInfo.getSex();
        Integer age = userWorkInfo.getAge();
        if(StringUtils.isEmpty(departmentid))
            return R.error().message("请选择部门");
        if(StringUtils.isEmpty(name))
            return R.error().message("输入姓名");
        if(StringUtils.isEmpty(phone))
            return R.error().message("输入手机号");
        if(StringUtils.isEmpty(sex))
            return R.error().message("请填写性别");
        if(StringUtils.isEmpty(salary))
            return R.error().message("请填写工资");
        if(StringUtils.isEmpty(age))
            return R.error().message("请填写性别");
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        userWorkInfo.setUserid(jwtInfo.getId());
        UserWorkInfo user = userWorkInfoService.getById(userWorkInfo);
        if (user != null && user.getStatis().equals("wait")) {
            return R.ok().message("正在等待批准，请勿重复提交！");
        }
        userWorkInfo.setStatis("wait");
        userWorkInfoService.saveOrUpdate(userWorkInfo);
        //下面插入申请记录数据
        MApplyDepartment m = new MApplyDepartment();
        m.setApplicationid(jwtInfo.getId());
        m.setDepartmentid(userWorkInfo.getDepartmentid());
        m.setDetail(userWorkInfo.toString());
        mApplyDepartmentService.save(m);

        return R.ok().message("提交申请成功");
    }

    /**
     * 人事组直接修改指定用户工作信息的方法
     *
     * @param userWorkInfo
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/updateNormalWorkInfoByUserId/{id}")
    public R updateNormalWorkInfoByUserId(@RequestBody UserWorkInfo userWorkInfo, HttpServletRequest request, @PathVariable String id) {
        //调用方法判断权限
        boolean result = isGetPower(request, 8);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        Integer departmentid = userWorkInfo.getDepartmentid();
        String name = userWorkInfo.getName();
        String phone = userWorkInfo.getPhone();
        Integer salary = userWorkInfo.getSalary();
        String sex = userWorkInfo.getSex();
        Integer age = userWorkInfo.getAge();
        if(StringUtils.isEmpty(departmentid))
            return R.error().message("请选择部门");
        if(StringUtils.isEmpty(name))
            return R.error().message("输入姓名");
        if(StringUtils.isEmpty(phone))
            return R.error().message("输入手机号");
        if(StringUtils.isEmpty(sex))
            return R.error().message("请填写性别");
        if(StringUtils.isEmpty(salary))
            return R.error().message("请填写工资");
        if(StringUtils.isEmpty(age))
            return R.error().message("请填写性别");
        userWorkInfo.setUserid(id);//根据点击用户的ID进行修改
        userWorkInfoService.saveOrUpdate(userWorkInfo);
        return R.ok().message("记录修改成功");
    }

    @PostMapping("/updateGreatWorkInfoByUserId/{id}")
    public R updateGreatWorkInfoByUserId(@RequestBody UserWorkInfo userWorkInfo, HttpServletRequest request, @PathVariable String id) {
        //调用方法判断权限
        boolean result = isGetPower(request, 9);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        Integer departmentid = userWorkInfo.getDepartmentid();
        String name = userWorkInfo.getName();
        String phone = userWorkInfo.getPhone();
        Integer salary = userWorkInfo.getSalary();
        String sex = userWorkInfo.getSex();
        Integer age = userWorkInfo.getAge();
        if(StringUtils.isEmpty(departmentid))
            return R.error().message("请选择部门");
        if(StringUtils.isEmpty(name))
            return R.error().message("输入姓名");
        if(StringUtils.isEmpty(phone))
            return R.error().message("输入手机号");
        if(StringUtils.isEmpty(sex))
            return R.error().message("请填写性别");
        if(StringUtils.isEmpty(salary))
            return R.error().message("请填写工资");
        if(StringUtils.isEmpty(age))
            return R.error().message("请填写性别");
        userWorkInfo.setUserid(id);
        userWorkInfoService.saveOrUpdate(userWorkInfo);
        return R.ok().message("记录修改成功成功");
    }

    /**
     * 审批普通用户时查询的方法
     *
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getNormalDealInfo/{current}/{size}")
    public R getNormalDealInfo(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                               @RequestBody(required = false) WorkInfoQuery workInfoQuery) {
        //调用方法判断权限
        boolean result = isGetPower(request, 1);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }

        QueryWrapper<UserWorkInfo> queryWrapper = new QueryWrapper<>();
        Page<UserWorkInfo> page = new Page<>(current, size);
        String name = workInfoQuery.getName();
        String sex = workInfoQuery.getSex();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(sex)) {
            queryWrapper.eq("sex", sex);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("gmt_modified", joinDateEnd);
        }

        queryWrapper.eq("statis", "wait");
        queryWrapper.eq("departmentid", 4);

        queryWrapper.orderByAsc("gmt_create");
        userWorkInfoService.page(page, queryWrapper);// 自动将结果封装到page中
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    /**
     * 修改普通用户工作信息的查询方法，自定sql语句，查询出部门名称
     *
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getNormalDealInfoPlus/{current}/{size}")
    public R getNormalDealInfoPlus(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                                   @RequestBody(required = false) WorkInfoQuery workInfoQuery) {
        //调用方法判断权限
        boolean result = isGetPower(request, 8);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<UserWorkInfoPlus> queryWrapper = new QueryWrapper<>();
        String name = workInfoQuery.getName();
        String sex = workInfoQuery.getSex();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("w.name", name);
        }
        if (!StringUtils.isEmpty(sex)) {
            queryWrapper.eq("w.sex", sex);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("w.gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("w.gmt_modified", joinDateEnd);
        }
        queryWrapper.eq("w.statis", "pass");//只查询出审核通过的员工
        queryWrapper.eq("w.departmentid", 4);//根据需求查询不同阶层的员工信息
        IPage<UserWorkInfoPlus> pageModel = userWorkInfoService.selectPage(current, size, workInfoQuery, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageModel.getTotal());
        map.put("list", pageModel.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    /**
     * 审批管理组用户的查询方法，
     *
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getGreatDealInfo/{current}/{size}")
    public R getGreatDealInfo(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                              @RequestBody(required = false) WorkInfoQuery workInfoQuery) {
        //调用方法判断权限
        boolean result = isGetPower(request, 2);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<UserWorkInfo> queryWrapper = new QueryWrapper<>();
        Page<UserWorkInfo> page = new Page<>(current, size);
        String name = workInfoQuery.getName();
        Integer departmentid = workInfoQuery.getDepartmentid();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(departmentid)) {
            queryWrapper.eq("departmentid", departmentid);
        } else {
            queryWrapper.ne("departmentid", 4);
            queryWrapper.ne("departmentid", 5);
        }

        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("gmt_modified", joinDateEnd);
        }

        queryWrapper.eq("statis", "wait");

        queryWrapper.orderByAsc("gmt_create");
        userWorkInfoService.page(page, queryWrapper);// 自动将结果封装到page中
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    /**
     * 部门员工信息管理查询方法
     *
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getGreatDealInfoPlus/{current}/{size}")
    public R getGreatDealInfoPlus(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                                  @RequestBody(required = false) WorkInfoQuery workInfoQuery) {
        //调用方法判断权限
        boolean result = isGetPower(request, 9);
        if (!result) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<UserWorkInfoPlus> queryWrapper = new QueryWrapper<>();
        String name = workInfoQuery.getName();
        Integer departmentid = workInfoQuery.getDepartmentid();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("w.name", name);
        }
        if (!StringUtils.isEmpty(departmentid)) {
            queryWrapper.eq("w.departmentid", departmentid);
        } else {
            queryWrapper.ne("w.departmentid", 4);
            queryWrapper.ne("w.departmentid", 5);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("w.gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("w.gmt_modified", joinDateEnd);
        }
        queryWrapper.eq("w.statis", "pass");//只查询出审核通过的员工
        IPage<UserWorkInfoPlus> pageModel = userWorkInfoService.selectPage(current, size, workInfoQuery, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageModel.getTotal());
        map.put("list", pageModel.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }


    /**
     * 审批普通用户结果插入，邮件通知（要修改2张表）
     *
     * @param request
     * @param userid
     * @param result
     * @return
     */
    @GetMapping("/dealNormalApply/{userid}/{result}")
    public R dealAllApply(HttpServletRequest request, @PathVariable String userid, @PathVariable boolean result) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        //调用方法判断权限
        boolean is = isGetPower(request, 1);
        if (!is) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        UserWorkInfo workInfo = new UserWorkInfo();
        workInfo.setUserid(userid);

        UpdateWrapper<MApplyDepartment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("applicationid", userid);
        updateWrapper.eq("statis", "wait");
        if (result) {
            workInfo.setStatis("pass");
            updateWrapper.set("statis", "pass");
        } else {
            workInfo.setStatis("defeat");
            updateWrapper.set("statis", "defeat");
        }
        updateWrapper.set("dealuserid", jwtInfo.getId());
        //下面在申请记录表中修改数据
        boolean update = mApplyDepartmentService.update(updateWrapper);
        if(!update)
            return  R.ok().message("已经有人处理过了，无需处理!");
        userWorkInfoService.updateById(workInfo);

        //这里发送邮箱信息通知！
        redisProduct.publishMessage("userid", userid);
        return R.ok().message("处理成功!");
    }

    /**
     * 管理组用户审批结果插入，邮件通知，要修改2张表
     *
     * @param request
     * @param userid
     * @param result
     * @return
     */
    @GetMapping("/dealGreatApply/{userid}/{result}")
    public R dealGreatApply(HttpServletRequest request, @PathVariable String userid, @PathVariable boolean result) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        //调用方法判断权限
        boolean is = isGetPower(request, 2);
        if (!is) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        UserWorkInfo workInfo = new UserWorkInfo();
        workInfo.setUserid(userid);

        UpdateWrapper<MApplyDepartment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("applicationid", userid);
        updateWrapper.eq("statis", "wait");
        if (result) {
            workInfo.setStatis("pass");
            updateWrapper.set("statis", "pass");
        } else {
            workInfo.setStatis("defeat");
            updateWrapper.set("statis", "defeat");
        }
        updateWrapper.set("dealuserid", jwtInfo.getId());
        //下面更改申请记录表中的信息
        boolean update = mApplyDepartmentService.update(updateWrapper);
        if(!update)
            return  R.ok().message("已经有人处理过了，无需处理!");
        userWorkInfoService.updateById(workInfo);

        //这里发送邮箱信息通知！
        redisProduct.publishMessage("userid", userid);

        return R.ok().message("处理成功!");
    }
    /**
     * 根据部门ID查询userlist集合
     */
@GetMapping("/getUserListByDepartment/{id}")
    public R getUserListByDepartment(HttpServletRequest request,@PathVariable String id ){
//    JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
//    //调用方法判断权限
//    boolean is = isGetPower(request, 11);
//    if (!is) {
//        throw new MyException(ResultCodeEnum.NO_POWER);
//    }
    //上面的方法后台管理系统用到了，所以暂时不判断权限
    QueryWrapper<UserWorkInfo> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("departmentid", id);
    queryWrapper.eq("statis", "pass");
    List<UserWorkInfo> list=userWorkInfoService.list(queryWrapper);
    return R.ok().data("list",list);
}
    /**
     * 该方法用来判断用户是否有指定权限
     *
     * @param request
     * @return
     */
    public boolean isGetPower(HttpServletRequest request, Integer powerid) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        List<Power> list = userWorkInfoService.getUserPower(jwtInfo.getId());//自定义多表查询语句
        for (Power one : list) {
            if (one.getId() == powerid) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/getUserPower")
    public R getUserPower(HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        List<Power> list = userWorkInfoService.getUserPower(jwtInfo.getId());//自定义多表查询语句
        return R.ok().data("list", list);
    }

    public boolean isHasDepartment(String id){
        QueryWrapper<UserWorkInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid", id);
        queryWrapper.eq("statis", "pass");
        queryWrapper.select("departmentid");
        int count = userWorkInfoService.count(queryWrapper);
        if(count>0)
            return true;
        return false;

    }

}

