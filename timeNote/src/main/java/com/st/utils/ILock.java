package com.st.utils;

/**
 * @ClassName: ILock
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/10/15 23:47
 */
public interface ILock {

    //尝试获取锁
    boolean tryLock(String lockId,long timeoutSec);

    //释放锁
    void unlock(String lockId);
}
