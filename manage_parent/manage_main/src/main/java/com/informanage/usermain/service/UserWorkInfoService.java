package com.informanage.usermain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.UserWorkInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.VO.SendEmailVo;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;
import com.informanage.usermain.entity.VO.WorkInfoQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface UserWorkInfoService extends IService<UserWorkInfo> {

    List<Power> getUserPower(String id);

    SendEmailVo getSendEmailVo(String message);

    IPage<UserWorkInfoPlus> selectPage(int current, int size, WorkInfoQuery workInfoQuery, QueryWrapper<UserWorkInfoPlus> queryWrapper);
}
