package com.informanage.usermain.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.informanage.common.R;
import com.informanage.common.RedisConfig;
import com.informanage.usermain.entity.UserWorkInfo;
import com.informanage.usermain.entity.VO.SendEmailVo;
import com.informanage.usermain.feignlient.SendInfoClient;
import com.informanage.usermain.service.UserWorkInfoService;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 该类用户去除redis集合中最前面一个
 */
@Service
public class RedisDepartmentConsumer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SendInfoClient sendInfoClient;
    @Autowired
    UserWorkInfoService userWorkInfoService;

    private  ListOperations<String, String> listRedis;


    @PostConstruct//构造方法执行后，bean注入后执行这个注解标识的方法
    private void init(){
        listRedis = stringRedisTemplate.opsForList();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void receiveDepartment(){
        System.out.println("定时任务启动！");
//        while (true){
            String message = listRedis.rightPop("userid");
            if(message != null && !message.isEmpty()){
                SendEmailVo vo=userWorkInfoService.getSendEmailVo(message);
                if(vo.getEmail()!=null&&!vo.getEmail().isEmpty()) {
                    StringBuilder str = new StringBuilder();
                    str.append("尊敬的：")
                            .append(vo.getUsername())
                            .append(",你好！")
                            .append("您申请的部门：")
                            .append(vo.getDepartmentname())
                            .append(",已经审核完毕，审核结果为：")
                            .append(vo.getStatis());
                    sendInfoClient.sendApplyResult(vo.getEmail(), str.toString());
                    System.out.println("已为一个用户发送邮件，用户名为： " + vo.getUsername());
                }
            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

}
