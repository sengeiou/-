package com.informanage.usermain.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 这个类用来将消息存入redis集合中最后
 */
@Component
public class RedisProduct {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ListOperations<String, String> listRedis;

    @PostConstruct
    private void init(){
        listRedis = stringRedisTemplate.opsForList();
    }

    public void publishMessage(String key,String message){
        listRedis.leftPush(key, message);
    }

}
