package com.informanage.usermain.mapper;

import com.informanage.usermain.entity.Power;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.informanage.usermain.entity.VO.PowerAminList;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface PowerMapper extends BaseMapper<Power> {

    List<PowerAminList> adminPowerList();
}
