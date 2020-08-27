package com.informanage.usermain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.MApplySpend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报销申请表 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
public interface MApplySpendMapper extends BaseMapper<MApplySpend> {

    PaySalary payMoney(String id);

    List<ApplySpendPlus> selectMyPage(Page<ApplySpendPlus> pageParam,
                                      @Param(Constants.WRAPPER)QueryWrapper<ApplyInfoQuery> queryWrapper);
}
