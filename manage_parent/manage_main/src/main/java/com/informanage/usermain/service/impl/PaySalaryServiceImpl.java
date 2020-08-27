package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.PaySalary;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.AllSalaryRecords;
import com.informanage.usermain.entity.VO.ApplyInfoQuery;
import com.informanage.usermain.entity.VO.ApplySpendPlus;
import com.informanage.usermain.entity.VO.PaySalaryVo;
import com.informanage.usermain.entity.admin.BarSalary;
import com.informanage.usermain.entity.admin.PieSalaryType;
import com.informanage.usermain.entity.admin.QueryVoByTime;
import com.informanage.usermain.feignlient.SendInfoClient;
import com.informanage.usermain.mapper.PaySalaryMapper;
import com.informanage.usermain.service.PaySalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工资发放记录表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-08
 */
@Service
public class PaySalaryServiceImpl extends ServiceImpl<PaySalaryMapper, PaySalary> implements PaySalaryService {
@Autowired
PaySalaryMapper mapper;
@Autowired
    SendInfoClient client;
    @Override
    public IPage<ApplySpendPlus> selectPage(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper) {
        Page<ApplySpendPlus> page = new Page<>(current, size);
        List<ApplySpendPlus> records = mapper.selectMyPage(page, queryWrapper);
        return page.setRecords(records);
    }

    @Override
    public void autoPaySalary() {
        List<PaySalaryVo> list=mapper.autoPaySalary();
        for (PaySalaryVo paySalary : list) {
            PaySalary p=new PaySalary();
            BeanUtils.copyProperties(paySalary,p);
            p.setDealname("自动发放").setDealid("0000000000000000000");
            mapper.insert(p);
            if(paySalary.getEmail()!=null) {
                //发邮件通知！或者发送短信通知！(手机号也在vo类中)
                StringBuilder content = new StringBuilder();
                content.append("尊敬的:").append(paySalary.getName())
                        .append(" 你好！您本月的工资已经发放，")
                        .append(paySalary.getSalary()).append("已经到账，请查收！");
                client.sendApplyResult(paySalary.getEmail(), content.toString());
            }else{
                //这里发送短信！
                System.out.println("已经发出一条短信");
            }
        }
    }

    @Override
    public PaySalaryVo sendInfoByTableId(String message) {
        PaySalaryVo vo= mapper.sendInfoByTableId(message);
        return vo;
    }

    @Override
    public IPage<AllSalaryRecords> selectPageAllSalary(int current, int size, ApplyInfoQuery workInfoQuery, QueryWrapper<ApplyInfoQuery> queryWrapper) {
        Page<AllSalaryRecords> page = new Page<>(current, size);
        List<AllSalaryRecords> records = mapper.selectPageAllSalary(page, queryWrapper);
        return page.setRecords(records);
    }

    @Override
    public List<BarSalary> getBarSalary() {
        List<BarSalary> list=  baseMapper.getBarSalary();
        return list;
    }

    @Override
    public List<BarSalary> getBarSalaryByYear(QueryWrapper<PaySalary> queryWrapper) {
        List<BarSalary> list=  baseMapper.getBarSalaryByYear(queryWrapper);
        return list;
    }

    @Override
    public List<PieSalaryType> getSalaryType(QueryWrapper<PieSalaryType> queryWrapper) {
        return baseMapper.getSalaryType(queryWrapper);
    }
}
