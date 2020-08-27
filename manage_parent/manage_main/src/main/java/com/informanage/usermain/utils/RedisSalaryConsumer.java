package com.informanage.usermain.utils;

import com.informanage.usermain.entity.VO.PaySalaryVo;
import com.informanage.usermain.feignlient.SendInfoClient;
import com.informanage.usermain.service.PaySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RedisSalaryConsumer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SendInfoClient sendInfoClient;
    @Autowired
    private PaySalaryService paySalaryService;
    @Autowired
    SendInfoClient client;
    private ListOperations<String, String> listRedis;

    @PostConstruct//构造方法执行后，bean注入后执行这个注解标识的方法
    private void init(){
        listRedis = stringRedisTemplate.opsForList();
    }
    @Scheduled(cron = "0/5 * * * * ?")
    public void receiveDepartment(){
        String message = listRedis.rightPop("salary");
        PaySalaryVo vo=paySalaryService.sendInfoByTableId(message);
        if (vo != null) {
            if(vo.getEmail()!=null&&vo.getEmail()!=""){
                StringBuilder content = new StringBuilder();
                content.append("尊敬的:").append(vo.getName())
                        .append(" 你好！您申请的补贴已经发放，")
                        .append(vo.getSalary()).append("已经到账，请查收！");
                client.sendApplyResult(vo.getEmail(), content.toString());
            }else{
                //这里发送短信。号码也在vo中
                System.out.println("发送了一条短信");
            }
        }

    }
}
