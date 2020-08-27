package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.SendEmailVo;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;
import com.informanage.usermain.entity.VO.WorkInfoQuery;
import com.informanage.usermain.mapper.UserWorkInfoMapper;
import com.informanage.usermain.service.UserWorkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@Service
public class UserWorkInfoServiceImpl extends ServiceImpl<UserWorkInfoMapper, UserWorkInfo> implements UserWorkInfoService {
@Autowired
UserWorkInfoMapper userWorkInfoMapper;
    @Override
    public List<Power> getUserPower(String id) {
        List<Power> list= userWorkInfoMapper.getUserPower(id);
        return list;
    }

    @Override
    public SendEmailVo getSendEmailVo(String message) {
        SendEmailVo sendEmailVo=userWorkInfoMapper.getSendEmailVo(message);
        return sendEmailVo;
    }

    @Override
    public IPage<UserWorkInfoPlus> selectPage(int current, int size, WorkInfoQuery workInfoQuery
    , QueryWrapper<UserWorkInfoPlus> queryWrapper) {
        // 组装分页
        Page<UserWorkInfoPlus> pageParam = new Page<>(current, size);
        // 执行分页查询
        // 只需要在mapper层传入封装好的分页组件即可，sql分页条件组装的过程由mp自动完成
        List<UserWorkInfoPlus> records = userWorkInfoMapper.selectWorkInfoPage(pageParam, queryWrapper);
        // 将records设置到pageParam中
        return pageParam.setRecords(records);
    }
}
