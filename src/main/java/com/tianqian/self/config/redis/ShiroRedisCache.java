/**
 * May 5, 2017 4:43:10 PM 
 * Copyright(c) 2015-2017 深圳***电子商务科技有限公司. 
 */
package com.tianqian.self.config.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate<String, V> redisTemplate;

    private String cacheKeyPrefix;

    private long expire;

    protected ShiroRedisCache(RedisTemplate redisTemplate,String name,long expire) {
        this.redisTemplate = redisTemplate;
        this.cacheKeyPrefix = name;
        this.expire = expire;
    }

    @Override
    public V get(K key) throws CacheException {
        return redisTemplate.opsForValue().get(this.cacheKeyPrefix + key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        redisTemplate.opsForValue().set(this.cacheKeyPrefix + key, value, this.expire, TimeUnit.MILLISECONDS);
        return previos;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        redisTemplate.delete(this.cacheKeyPrefix + key);
        return previos;
    }

    @Override
    public void clear() throws CacheException {
        Set<String> redisKeys = redisTemplate.keys(this.cacheKeyPrefix + "*");
        for (String redisKey : redisKeys) {
            redisTemplate.delete(redisKey);
        }
    }

    @Override
    public int size() {
        if(keys() == null){
            return 0;
        }
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        Set<String> redisKeys = redisTemplate.keys(this.cacheKeyPrefix + "*");
        Set<K> keys = new HashSet<>();
        for (String redisKey : redisKeys) {
            keys.add((K) redisKey.substring(this.cacheKeyPrefix.length()));
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Set<String> redisKeys = redisTemplate.keys(this.cacheKeyPrefix + "*");
        Set<V> values = new HashSet<>();
        for (String redisKey : redisKeys) {
            V value = redisTemplate.opsForValue().get(redisKey);
            values.add(value);
        }
        return values;
    }
}
