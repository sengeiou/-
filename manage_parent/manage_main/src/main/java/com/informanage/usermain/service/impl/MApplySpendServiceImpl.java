package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.MApplySpend;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.mapper.MApplySpendMapper;
import com.informanage.usermain.service.MApplySpendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报销申请表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
@Service
public class MApplySpendServiceImpl extends ServiceImpl<MApplySpendMapper, MApplySpend> implements MApplySpendService {
    @Autowired
    MApplySpendMapper mApplySpendMapper;

    @Override
    public PaySalary payMoney(String userid) {
        PaySalary paySalary = mApplySpendMapper.payMoney(userid);
        return paySalary;
    }

    @Override
    public IPage<ApplySpendPlus> selectPage(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper) {
        // 组装分页
        Page<ApplySpendPlus> pageParam = new Page<>(current, size);
        List<ApplySpendPlus> records = mApplySpendMapper.selectMyPage(pageParam, queryWrapper);
        return pageParam.setRecords(records);
    }
}
