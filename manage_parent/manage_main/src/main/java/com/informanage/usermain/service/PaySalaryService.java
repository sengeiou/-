package com.informanage.usermain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.informanage.usermain.entity.PaySalary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.VO.AllSalaryRecords;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.entity.VO.PaySalaryVo;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.PieSalaryType;
import com.informanage.usermain.entity.admin.QueryVoByTime;

import java.util.List;

/**
 * <p>
 * 工资发放记录表 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
public interface PaySalaryService extends IService<PaySalary> {

    IPage<ApplySpendPlus> selectPage(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper);

    void autoPaySalary();

    PaySalaryVo sendInfoByTableId(String message);

    IPage<AllSalaryRecords> selectPageAllSalary(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper);

    List<BarSalary> getBarSalary();

    List<BarSalary> getBarSalaryByYear(QueryWrapper<PaySalary> queryWrapper);

    List<PieSalaryType> getSalaryType(QueryWrapper<PieSalaryType> queryWrapper);
}
