package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.informanage.common.*;
import com.informanage.usermain.entity.MApplySpend;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.VO.AllSalaryRecords;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.service.PaySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 工资发放记录表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/usermain/pay-salary")
@CrossOrigin
public class PaySalaryController {
    @Autowired
    PaySalaryService paySalaryService;
    @Autowired
    private UserWorkInfoController workInfoController;

    @PostMapping("/getMyMoneyPay/{current}/{size}")
    public R getMyMoneyPay(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                           @RequestBody(required = false) ApplyInfoQuery workInfoQuery) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean hasDepartment = workInfoController.isHasDepartment(jwtInfo.getId());
        if (!hasDepartment) {
            return R.error().message("请先完成部门信息审核！");
        }
        QueryWrapper<ApplyInfoQuery> queryWrapper = new QueryWrapper<>();
        Integer begMoney = workInfoQuery.getApplyMoneyBegin();
        Integer applyMoneyEnd = workInfoQuery.getApplyMoneyEnd();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        String statis = workInfoQuery.getStatis();
        if (!StringUtils.isEmpty(statis)) {
            queryWrapper.eq("a.statis", statis);
        }
        if (!StringUtils.isEmpty(begMoney)) {
            queryWrapper.ge("a.money", begMoney);
        }
        if (!StringUtils.isEmpty(applyMoneyEnd)) {
            queryWrapper.le("a.money", applyMoneyEnd);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("a.gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("a.gmt_modified", joinDateEnd);
        }
        queryWrapper.eq("a.applicationid", jwtInfo.getId());
        IPage<ApplySpendPlus> pageModel = paySalaryService.selectPage(current, size, workInfoQuery, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageModel.getTotal());
        map.put("list", pageModel.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }

    /**
     * 获取全部用户工资发放记录表
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getAllMoneyPay/{current}/{size}")
    public R getAllMoneyPay(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                           @RequestBody(required = false) ApplyInfoQuery workInfoQuery) {
        boolean getPower = workInfoController.isGetPower(request, 3);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }

        QueryWrapper<ApplyInfoQuery> queryWrapper = new QueryWrapper<>();
        Integer begMoney = workInfoQuery.getApplyMoneyBegin();
        Integer applyMoneyEnd = workInfoQuery.getApplyMoneyEnd();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        String name = workInfoQuery.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("w.name", name);
        }
        if (!StringUtils.isEmpty(begMoney)) {
            queryWrapper.ge("p.salary", begMoney);
        }
        if (!StringUtils.isEmpty(applyMoneyEnd)) {
            queryWrapper.le("p.salary", applyMoneyEnd);
        }
        if (!StringUtils.isEmpty(joinDateBegin)) {
            queryWrapper.ge("p.gmt_create", joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)) {
            queryWrapper.le("p.gmt_modified", joinDateEnd);
        }
        IPage<AllSalaryRecords> pageModel = paySalaryService.selectPageAllSalary(current, size, workInfoQuery, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageModel.getTotal());
        map.put("list", pageModel.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }
    /**
     * 根据ID删除工资发放记录
     */
    @GetMapping("/deleteById/{id}")
    public R deleteById(HttpServletRequest request,@PathVariable String id){
        boolean getPower = workInfoController.isGetPower(request, 3);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        paySalaryService.removeById(id);
        return  R.ok().message("删除成功！");
    }

    /**
     * 手动发工资，前端未写页面，此接口未调用
     * @param paySalary
     * @param request
     * @return
     */
    @PostMapping("/paySalary")
    public R paySalary(@RequestBody PaySalary paySalary, HttpServletRequest request) {
        if (StringUtils.isEmpty(paySalary.getDepartmentid())) {
            return R.error().message("请选择部门");
        }
        if (StringUtils.isEmpty(paySalary.getUserid())) {
            return R.error().message("请选收款人");
        }
        if (StringUtils.isEmpty(paySalary.getSalary())) {
            return R.error().message("请设置金额");
        }
        boolean getPower = workInfoController.isGetPower(request, 11);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        paySalary.setDealid(jwtInfo.getId()).setDealname("员工");
        paySalaryService.save(paySalary);
        return R.ok().message("发放成功！");
    }

    /**
     * 自动发放工资的任务！
     */
    @Scheduled(cron = "0 0 1 1 * ?")//每月1号晚上1点执行一次
    private void autoPaySalary(){
        paySalaryService.autoPaySalary();
    }
}

