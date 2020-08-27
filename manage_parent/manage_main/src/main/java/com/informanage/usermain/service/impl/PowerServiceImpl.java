package com.informanage.usermain.service.impl;

import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.VO.PowerAminList;
import com.informanage.usermain.mapper.PowerMapper;
import com.informanage.usermain.service.PowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements PowerService {

    @Override
    @Cacheable(value="listPower")
    public List<PowerAminList> adminPowerList() {
        return baseMapper.adminPowerList();
    }
}
