package com.informanage.usermain.service;

import com.informanage.usermain.entity.Power;
import com.baomidou.mybatisplus.extension.service.IService;
import com.informanage.usermain.entity.VO.PowerAminList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
public interface PowerService extends IService<Power> {

    List<PowerAminList> adminPowerList();
}
