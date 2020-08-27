package com.informanage.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;


import com.informanage.oss.service.FileService;
import com.informanage.oss.utils.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {

        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)) {
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        //构建objectName：文件路径 avatar/2020/04/15/default.jpg
        String folder = new DateTime().toString("yyyy/MM/dd");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = null;
        if (!originalFilename.contains(".")) {
            //自定义名称得文件，需要手动判断
            System.out.println("oss服务上传未定义类型文件！文件名：" + originalFilename);
            fileExtension = ".png";
        } else {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String key = module + "/" + folder + "/" + fileName + fileExtension;

            // 上传文件流。
            ossClient.putObject(bucketname, key, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回url
            //https://guli-file-191125.oss-cn-beijing.aliyuncs.com/avatar/default.jpg
            return "https://" + bucketname + "." + endpoint + "/" + key;
        }

}

