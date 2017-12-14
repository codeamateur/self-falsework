//package com.tianqian.self.config.distributedlock;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//@Component
//public class RedisDistributedLockHelper {
//
//    private static Logger logger = LoggerFactory.getLogger(RedisDistributedLockHelper.class);
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 锁前缀
//     */
//    private String lockKeyPrefix = "lock:";
//
//    /**
//     * 获取锁可重试时间
//     */
//    private static final Long TRY_TIME_OUT = 30L;
//
//    /**
//     * 获取锁过期时间
//     */
//    private static final Long EXPIRE = 60L;
//
//    /**
//     * 获取锁
//     * @param key
//     * @return
//     */
//    public boolean tryLock(String key) {
//        return tryLock(key, TRY_TIME_OUT, EXPIRE, TimeUnit.SECONDS, true);
//    }
//
//    /**
//     * 获取redis锁
//     * @param key redis key
//     * @param tryTimeOut 重试超时时间
//     * @param expire 锁有效时间
//     * @param unit 时间单位
//     * @param isExpire 是否设置有效时间
//     * @return
//     */
//    public boolean tryLock(String key, long tryTimeOut,long expire,TimeUnit unit,boolean isExpire) {
//        long nanoTime = System.nanoTime();
//        Random random = new Random();
//        try {
//            do {
//                boolean exec = redisTemplate.opsForValue().setIfAbsent(lockKeyPrefix+key, key);
//                if (exec) {
//                    if (isExpire) {
//                        redisTemplate.expire(lockKeyPrefix+key, expire, unit);
//                    }
//                    return Boolean.TRUE;
//                }
//                if (tryTimeOut == 0) {
//                    break;
//                }
//                Thread.sleep(50, random.nextInt(500));
//            } while ((System.nanoTime() - nanoTime) < unit.toNanos(tryTimeOut));
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//        }
//        return Boolean.FALSE;
//    }
//
//    public void unLock(String key){
//        redisTemplate.delete(lockKeyPrefix+key);
//
///*        String unlockScript = "if (redis.call('get', KEYS[1]) == ARGV[1]) then "
//                + "    return redis.call('del', KEYS[1]); "
//                + "else "
//                + "    return nil; "
//                + "end;";
//        Long result = (Long) redisTemplate.execute(new DefaultRedisScript<Long>(unlockScript, Long.class), Collections.singletonList(lockKeyPrefix+key), Collections.singletonList(key));
//        if(result == null){
//            logger.info("Fail to unlock for key:{},maybe lock is already expired",key);
//        }*/
//    }
//}