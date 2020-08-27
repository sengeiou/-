package com.informanage.usermain.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.informanage.common.R;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.PieSalaryType;
import com.informanage.usermain.entity.admin.QueryVoByTime;
import com.informanage.usermain.service.PaySalaryService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usermain/pay-salary/admin")
@CrossOrigin
public class AdminSalaryController {

    @Autowired
    PaySalaryService paySalaryService;

    /**
     * 获取当前时间工作信息表中工资的分布情况（柱状图）
     * @return
     */
    @GetMapping("/getBarSalary")
    public R getBarSalary(){
        List<BarSalary> list=paySalaryService.getBarSalary();
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<Integer> numlist = new ArrayList<>();
        ArrayList<ArrayList<Integer>> listout = new ArrayList<>();
        for (BarSalary barSalary : list) {
        ArrayList<Integer> inner = new ArrayList<>();
        namelist.add(barSalary.getSalary());
        numlist.add(barSalary.getNum());
        inner.add(Integer.valueOf(barSalary.getSalary()));
        inner.add(barSalary.getNum());
            listout.add(inner);
        }
        return  R.ok().data("namelist",namelist).data("numlist",numlist).data("list",listout);
    }

    /**
     * 根据传过来的年份条件查询指定范围的工资发放表中salary分布情况
     */

    @PostMapping("/getBarSalaryByYear")
    public R getBarSalaryByYear(@RequestBody QueryVoByTime queryVoByTime){
        String startTime = queryVoByTime.getStartTime();
        String endTime = queryVoByTime.getEndTime();
        QueryWrapper<PaySalary> queryWrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(startTime))
            queryWrapper.gt("gmt_create", startTime);
        if(!StringUtils.isEmpty(endTime))
            queryWrapper.lt("gmt_create", endTime);
        queryWrapper.groupBy("salary");
        List<BarSalary> list=paySalaryService.getBarSalaryByYear(queryWrapper);
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<Integer> numlist = new ArrayList<>();
        for (BarSalary barSalary : list) {
            namelist.add(barSalary.getSalary());
            numlist.add(barSalary.getNum());
        }
        return  R.ok().data("namelist",namelist).data("numlist",numlist);
    }
    /**
     * 查询资金申请各个类型的记录条数
     */
    @PostMapping("/getSalaryType")
    public R getSalaryType(@RequestBody QueryVoByTime queryVoByTime){
        String startTime = queryVoByTime.getStartTime();
        String endTime = queryVoByTime.getEndTime();
        QueryWrapper<PieSalaryType> queryWrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(startTime))
            queryWrapper.gt("gmt_create", startTime);
        if(!StringUtils.isEmpty(endTime))
            queryWrapper.lt("gmt_create", endTime);
        List<PieSalaryType> list=paySalaryService.getSalaryType(queryWrapper);
        return  R.ok().data("list",list);
    }

}
