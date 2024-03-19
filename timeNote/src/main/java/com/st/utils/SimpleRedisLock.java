package com.st.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SimpleRedisLock
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/15 23:49
 */
public class SimpleRedisLock implements ILock{

    private StringRedisTemplate stringRedisTemplate;

    private String lockId;

    public SimpleRedisLock(String lockId,StringRedisTemplate stringRedisTemplate){
        this.lockId = lockId;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryLock(String lockId,long timeoutSec) {
        //获取锁
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockId, lockId,timeoutSec,TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public void unlock(String lockId) {
        //释放锁
        stringRedisTemplate.delete(lockId);
    }
}
