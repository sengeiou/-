package com.informanage.usermain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-11
 */
public interface UserService extends IService<User> {

    void myupdate(String id, int i);

    void mypage(Page<User> page, QueryWrapper<User> userQueryWrapper);
}
