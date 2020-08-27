package com.informanage.sendinfo.controller;

import java.util.concurrent.TimeUnit;

import com.informanage.common.*;
import com.informanage.sendinfo.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.aliyuncs.exceptions.ClientException;


@RestController
@CrossOrigin
@RequestMapping("/sendinfo")
public class MsmController {
@Autowired
private MsmService msmService;
@Autowired
private RedisTemplate<String ,String> redisTemplate;

@GetMapping("sendPhone/{mobile}")
public R getPhoneCode(@PathVariable String mobile) throws ClientException {

    //校验手机号是否合法
    if(StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)){
    	throw new MyException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
       // return R.error().message("手机号不正确").code(28001);
    }

    //生成验证码
    String checkCode = RandomUtils.getFourBitRandom();

    //发送验证码
    msmService.send(mobile, checkCode);

    //存储验证码到redis
    redisTemplate.opsForValue().set(mobile, checkCode, 5, TimeUnit.MINUTES);

    return R.ok().message("短信发送成功");
}


    @GetMapping("sendEmail/{email}")
    public R getEmailCode(@PathVariable String email) throws ClientException {
        if(StringUtils.isEmpty(email) || !FormUtils.isEmail(email)){
            throw new MyException(ResultCodeEnum.LOGIN_Email_ERROR);
        }

        //生成验证码
        String checkCode = RandomUtils.getFourBitRandom();
        String content="欢迎注册404天团测试项目，您的验证码为："+checkCode+"，请在5分钟内完成注册，否则验证码将会失效！";
        //发送验证码
        msmService.sendEmail(email,"邮箱注册",content,"lbqq0519204zhio@163.com");

        //存储验证码到redis
        redisTemplate.opsForValue().set(email, checkCode, 5, TimeUnit.MINUTES);

        return R.ok().message("邮箱发送成功");
    }

    @PostMapping("sendApplyResult/{email}")
    public R sendApplyResult(@PathVariable String email,@RequestParam String message) throws ClientException {
        msmService.sendEmail(email,"申请结果",message,"lbqq0519204zhio@163.com");
        return R.ok().message("邮箱发送成功");
    }
}
