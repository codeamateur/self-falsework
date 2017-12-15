package com.tianqian.self.config.distributedlock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockHelper {
    private static Logger logger = LoggerFactory.getLogger(RedisDistributedLockHelper.class);

    @Autowired
    private RedissonClient redisson;

    /**
     * 锁前缀
     */
    private String lockKeyPrefix = "lock:";


    /**
     * 尝试获取锁
     * @param key 锁名
     * @param waitTime 等待时间
     * @param leaseTime 过期时间
     * @param unit 时间单位
     * @return
     */
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = redisson.getFairLock(lockKeyPrefix+key);
        try {
            return lock.tryLock(waitTime,leaseTime,unit);
        } catch (InterruptedException e) {
            logger.error("获取锁失败：",e);
            return false;
        }
    }

    /**
     * 解锁
     * @param key
     */
    public void unlock(String key){
        RLock lock = redisson.getFairLock(lockKeyPrefix+key);
        if(lock.isLocked()){
            lock.unlock();
        }
    }



}
