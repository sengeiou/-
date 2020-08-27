package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.informanage.common.R;
import com.informanage.usermain.entity.Department;
import com.informanage.usermain.entity.PowerDepartment;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.admin.ChangeDepartmentPower;
import com.informanage.usermain.service.DepartmentService;
import com.informanage.usermain.service.PowerDepartmentService;
import com.informanage.usermain.service.UserWorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@RequestMapping("/usermain/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserWorkInfoService service;
    @Autowired
    PowerDepartmentService powerDepartmentService;

    @GetMapping("/getAllDepartment")
    public R getAllDepartment() {
        List<Department> list = departmentService.departmentlist();
        return R.ok().data("list", list);
    }

    /**
     * 该方法返回部门权限列表，更改部门权限时调用
     *
     * @return
     */
    @GetMapping("/getAllDepartment2")
    public R getAllDepartment2() {
        List<ChangeDepartmentPower> list = departmentService.departmentlist2();//下面查询每个部门的权力
        for (ChangeDepartmentPower de : list) {
            QueryWrapper<PowerDepartment> q = new QueryWrapper<>();
            q.select("powerid");
            q.eq("departmentid", de.getId());
            List<PowerDepartment> list1 = powerDepartmentService.list(q);
            ArrayList<Integer> integers = new ArrayList<>();
            for (PowerDepartment powerDepartment : list1) {
                integers.add(powerDepartment.getPowerid());
            }
            de.setList(integers);
        }
        return R.ok().data("list", list);
    }

    @PostMapping("/changePower")
    public R changePower(@RequestBody List<ChangeDepartmentPower> changeDepartmentPower) {

        Runnable runnable = () -> {
            departmentService.changePower(changeDepartmentPower);
        };
        new Thread(runnable).start();
        return R.ok().message("权限修改成功！");
    }

    /**
     * 自动统计部门的人数
     */
    @Scheduled(cron = "0 0/30 * * * ? ")//每半小时执行一次
    private void getDepartmentPeople() {
        List<Department> list = departmentService.list();
        for (Department department : list) {
            QueryWrapper<UserWorkInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("departmentid", department.getId());
            int count = service.count(queryWrapper);
            department.setPeoplenumber(count);
            departmentService.updateById(department);
        }
    }


}

