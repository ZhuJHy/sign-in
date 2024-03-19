package com.st.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.alibaba.fastjson.util.IOUtils.close;

/**
 * @ClassName: RedisLockHelper
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/15 12:28
 */
@Component
@Slf4j
public class RedisLockHelper {

    @Autowired
    public RedisTemplate redisTemplate;

    public boolean lock(String lockId){
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockId,null);
        log.info("没有{}",flag);
        if (flag){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除锁
     * @param lockId
     */
    public void delete(String lockId) {
        redisTemplate.delete(lockId);
    }
}

