package com.informanage.usermain.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.informanage.common.R;
import com.informanage.usermain.entity.Power;
import com.informanage.usermain.entity.VO.PowerAminList;
import com.informanage.usermain.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LQ
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/usermain/power")
@CrossOrigin
public class PowerController {
@Autowired
    PowerService powerService;


@GetMapping("/getAllPower")
    public R getAllPower(){
    List<PowerAminList> list = powerService.adminPowerList();
    return R.ok().data("listall",list);
}

}

