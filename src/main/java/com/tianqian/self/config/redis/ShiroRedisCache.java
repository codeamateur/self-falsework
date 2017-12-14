/**
 * May 5, 2017 4:43:10 PM 
 * Copyright(c) 2015-2017 深圳***电子商务科技有限公司. 
 */
package com.tianqian.self.config.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.redisson.api.RMapCache;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    /**
     * 过期时间
     */
    private long expire;

    private RMapCache<K, V> authorizationCache;

    protected ShiroRedisCache(RMapCache<K, V> authorizationCache,long expire) {
        this.authorizationCache =authorizationCache;
        this.expire =expire;
    }

    @Override
    public V get(K key) throws CacheException {
        return authorizationCache.get(key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        return authorizationCache.put(key,value,expire,TimeUnit.MILLISECONDS);
    }

    @Override
    public V remove(K key) throws CacheException {
        return authorizationCache.remove(key);
    }

    @Override
    public void clear() throws CacheException {
        authorizationCache.clear();
    }

    @Override
    public int size() {
        if(keys() == null){
            return 0;
        }
        return keys().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        return authorizationCache.keySet();
    }

    @Override
    public Collection<V> values() {
        return authorizationCache.values();
    }
}
