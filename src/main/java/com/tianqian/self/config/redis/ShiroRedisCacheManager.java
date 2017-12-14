/**
 * May 5, 2017 4:25:52 PM 
 * Copyright(c) 2015-2017 深圳xxx电子商务科技有限公司. 
 */
package com.tianqian.self.config.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRedisCacheManager implements CacheManager, Destroyable {

    /**
     * redis cache key的前缀
     */
    private String cacheKeyPrefix;
    /**
     * 过期时间
     */
    private long expire = 1800000L;

    @Autowired
    private RedissonClient redisson;


    @Override
    public void destroy() throws Exception {
        redisson.getMapCache(cacheKeyPrefix).clear();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroRedisCache<K, V>(redisson.getMapCache(cacheKeyPrefix),expire);
    }

    /**
     * 需要spring注入，所以public访问权限
     * 
     * @param cacheKeyPrefix
     */
    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    /**
     * 配置过期时间
     * @param expire
     */
    public void setExpire(long expire) {
        this.expire = expire;
    }
}
