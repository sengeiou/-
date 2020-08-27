package com.informanage.user.feignlient.Impl;

import com.informanage.common.R;
import com.informanage.user.feignlient.OssClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class OssClientImpl implements OssClient {
    @Override
    public R upload(MultipartFile file, String module) {
        System.out.println("微服务调用oss存储头像失败");
        return R.error().message("微服务调用oss存储头像失败");
    }
}
