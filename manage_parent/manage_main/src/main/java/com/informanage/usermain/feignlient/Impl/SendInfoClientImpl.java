package com.informanage.usermain.feignlient.Impl;

import com.informanage.common.R;
import com.informanage.usermain.feignlient.SendInfoClient;
import org.springframework.stereotype.Component;

@Component
public class SendInfoClientImpl implements SendInfoClient {
    @Override
    public R sendApplyResult(String email,String mes) {
        System.out.println("微服务调用sendInfo发送邮箱失败,要发的对象为："+email+",要发送的信息为："+mes);
        return R.error().message("微服务调用oss存储头像失败");
    }
}
