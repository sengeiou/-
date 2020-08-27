package com.informanage.usermain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.informanage.usermain.entity.MApplySpend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;

/**
 * <p>
 * 报销申请表 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
public interface MApplySpendService extends IService<MApplySpend> {

    PaySalary payMoney(String userid);

    IPage<ApplySpendPlus> selectPage(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper);
}
