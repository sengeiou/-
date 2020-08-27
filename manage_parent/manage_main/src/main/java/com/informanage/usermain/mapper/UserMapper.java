package com.informanage.usermain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.informanage.usermain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-11
 */
public interface UserMapper extends BaseMapper<User> {

    void myupdate(@Param("id") String id,@Param("result") int i);

    List<User> mypage(Page<User> page,
                      @Param(Constants.WRAPPER) QueryWrapper<User> userQueryWrapper);
}
