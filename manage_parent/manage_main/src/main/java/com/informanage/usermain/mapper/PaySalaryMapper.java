package com.informanage.usermain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.PaySalary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.AllSalaryRecords;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.entity.VO.PaySalaryVo;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.PieSalaryType;
import com.informanage.usermain.entity.admin.QueryVoByTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工资发放记录表 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
public interface PaySalaryMapper extends BaseMapper<PaySalary> {

    List<ApplySpendPlus> selectMyPage(Page<ApplySpendPlus> page,
                                      @Param(Constants.WRAPPER)QueryWrapper<ApplyInfoQuery> queryWrapper);

    List<PaySalaryVo> autoPaySalary();

    PaySalaryVo sendInfoByTableId(String id);

    List<AllSalaryRecords> selectPageAllSalary(Page<AllSalaryRecords> page,
                                               @Param(Constants.WRAPPER)QueryWrapper<ApplyInfoQuery> queryWrapper);

    List<BarSalary> getBarSalary();

    List<BarSalary> getBarSalaryByYear( @Param(Constants.WRAPPER) QueryWrapper<PaySalary> queryWrapper);

    List<PieSalaryType> getSalaryType( @Param(Constants.WRAPPER) QueryWrapper<PieSalaryType> queryWrapper);
}
