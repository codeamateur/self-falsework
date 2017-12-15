/**
 * May 5, 2017 4:25:52 PM 
 * Copyright(c) 2015-2017 深圳xxx电子商务科技有限公司. 
 */
package com.tianqian.self.config.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ShiroRedisCacheManager implements CacheManager, Destroyable {

    /**
     * redis cache key的前缀
     */
    private String cacheKeyPrefix;
    /**
     * doGetAuthorizationInfo 的过期时间,默认30分钟。
     */
    private long expire = 1800000L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Override
    public void destroy() throws Exception {
        Set<String> redisKeys = redisTemplate.keys(this.cacheKeyPrefix + "*");
        for (String redisKey : redisKeys) {
            redisTemplate.delete(redisKey);
        }
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache c = caches.get(name);
        if(c == null){
           c = new ShiroRedisCache<K,V>(redisTemplate,cacheKeyPrefix,expire);
           caches.put(name, c);
        }
        return c;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
