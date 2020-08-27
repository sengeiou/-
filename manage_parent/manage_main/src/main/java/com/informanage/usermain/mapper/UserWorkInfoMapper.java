package com.informanage.usermain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.UserWorkInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.VO.SendEmailVo;
import com.informanage.usermain.entity.VO.UserWorkInfoPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface UserWorkInfoMapper extends BaseMapper<UserWorkInfo> {

    List<Power> getUserPower(String id);

    SendEmailVo getSendEmailVo(String id);

    List<UserWorkInfoPlus> selectWorkInfoPage(Page<UserWorkInfoPlus> pageParam,
                                              @Param(Constants.WRAPPER)QueryWrapper<UserWorkInfoPlus> queryWrapper);
}
