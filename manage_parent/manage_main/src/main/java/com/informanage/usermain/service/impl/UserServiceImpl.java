package com.informanage.usermain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.User;
import com.informanage.usermain.mapper.UserMapper;
import com.informanage.usermain.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-08-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void myupdate(String id, int i) {
        baseMapper.myupdate(id,i);
    }

    @Override
    public void mypage(Page<User> page, QueryWrapper<User> userQueryWrapper) {
       List<User> list=baseMapper.mypage(page,userQueryWrapper);
       page.setRecords(list);
    }
}
