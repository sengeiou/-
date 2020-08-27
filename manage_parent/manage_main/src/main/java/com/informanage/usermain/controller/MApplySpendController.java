package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.informanage.common.*;
import com.informanage.usermain.entity.MApplySpend;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;
import com.informanage.usermain.entity.VO.WorkInfoQuery;
import com.informanage.usermain.service.MApplySpendService;
import com.informanage.usermain.service.PaySalaryService;
import com.informanage.usermain.utils.RedisProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 报销申请表 前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/usermain/m-apply-spend")
@CrossOrigin
public class MApplySpendController {
    @Autowired
    private MApplySpendService spendService;
    @Autowired
    private UserWorkInfoController workInfoController;
    @Autowired
    private PaySalaryService paySalaryService;
    @Autowired
    private RedisProduct product;


    @PostMapping("/moneyapply")
    public R moneyapply(@RequestBody MApplySpend spend, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean hasDepartment = workInfoController.isHasDepartment(jwtInfo.getId());
        if(!hasDepartment){
           return R.error().message("请先完成部门信息审核！");
        }
        Integer money = spend.getMoney();
        String type = spend.getType();
        if(StringUtils.isEmpty(type))
            return R.error().message("请填写申报类型！");
        if(StringUtils.isEmpty(money))
            return R.error().message("请填写申报金额！");
        spend.setApplicationid(jwtInfo.getId()).setStatis("wait");
        boolean save = spendService.save(spend);
        if (save)
            return R.ok().message("申请提交成功！");
        return R.error().message(("申请保存失败！"));
    }

    /**
     * 审批
     * @param request
     * @param tableid
     * @param result
     * @return
     */
    @GetMapping("/dealApply/{tableid}/{result}")
    public R dealApply(HttpServletRequest request, @PathVariable String tableid, @PathVariable String result) {
        boolean getPower = workInfoController.isGetPower(request, 7);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        UpdateWrapper<MApplySpend> up = new UpdateWrapper<>();
        up.eq("id", tableid);
        up.eq("statis", "wait");//加状态，多个人修改只有一个人能够修改成功
        up.set("dealuserid", jwtInfo.getId());
        up.set("statis", result);
        boolean update = spendService.update(up);//影响行数大于等于1返回true
        if(!update)
            return R.ok().message("已经有人审批过了，无需审批！");
        if(result.equals("pass")) {
            //审批完成后在工资发放表中插入数据（表示发放工资）
            PaySalary paySalary = spendService.payMoney(tableid);
            //上面是自定义复杂sql,可能存在问题(已修复)
            paySalaryService.save(paySalary);
        }
        //下面发送邮件或者短信！
        product.publishMessage("salary", tableid);
        return R.ok().message("审批成功！");
    }


    /**
     * 同工作信息自定义sql分页多条件查询
     * @param request
     * @param current
     * @param size
     * @param workInfoQuery
     * @return
     */
    @PostMapping("/getWaitApply/{current}/{size}")
    public R getWaitApply(HttpServletRequest request, @PathVariable int current, @PathVariable int size,
                                   @RequestBody(required = false) ApplyInfoQuery workInfoQuery) {
        boolean getPower = workInfoController.isGetPower(request, 7);
        if (!getPower) {
            throw new MyException(ResultCodeEnum.NO_POWER);
        }
        QueryWrapper<ApplyInfoQuery> queryWrapper = new QueryWrapper<>();
        String name = workInfoQuery.getName();
        Integer begMoney=workInfoQuery.getApplyMoneyBegin();
        Integer applyMoneyEnd = workInfoQuery.getApplyMoneyEnd();
        String joinDateBegin = workInfoQuery.getApplyDateBegin();
        String joinDateEnd = workInfoQuery.getApplyDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("w.name", name);
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
        queryWrapper.eq("a.statis", "wait");
        IPage<ApplySpendPlus> pageModel = spendService.selectPage(current, size, workInfoQuery, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageModel.getTotal());
        map.put("list", pageModel.getRecords());
        return R.ok().data(map);//前端保存好userid，后端修改状态时需要userid
    }
}

