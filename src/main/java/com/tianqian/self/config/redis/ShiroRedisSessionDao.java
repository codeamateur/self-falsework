/**
 * May 5, 2017 2:20:53 PM 
 * Copyright(c) 2015-2017 深圳xxx电子商务科技有限公司. 
 */
package com.tianqian.self.config.redis;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisSessionDao extends AbstractSessionDAO {

    /**
     * redis session key 前缀
     */
    private String sessionKeyPrefix;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ShiroRedisSessionDao.class);

    @Override
    public void update(Session session) throws UnknownSessionException {
        redisTemplate.opsForValue().set(this.sessionKeyPrefix + session.getId(), session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(Session session) {
        redisTemplate.delete(this.sessionKeyPrefix + session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keys = redisTemplate.keys(this.sessionKeyPrefix + "*");
        Set<Session> sessions = new HashSet<Session>();
        for (String key : keys) {
            Session session = (Session)redisTemplate.opsForValue().get(key);
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.update(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = (Session)redisTemplate.opsForValue().get(this.sessionKeyPrefix + sessionId);
        return session;
    }

    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }
}
