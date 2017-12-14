
package com.tianqian.self.config.redis;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedissonClient redisson;

    /**
     * redis session key 前缀
     */
    private String sessionKeyPrefix;

    private static final Logger logger = LoggerFactory.getLogger(ShiroRedisSessionDao.class);

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.info("session or sessionId is null");
            return ;
        }
        RMapCache<Serializable, Session> sessionCache = redisson.getMapCache(sessionKeyPrefix);
        sessionCache.put(session.getId(),session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.info("session or sessionId is null");
            return ;
        }
        RMapCache<Serializable, Session> sessionCache = redisson.getMapCache(sessionKeyPrefix);
        sessionCache.remove(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        RMapCache<Serializable, Session> sessionCache = redisson.getMapCache(sessionKeyPrefix);
        Set<Serializable> keys = sessionCache.keySet();
        Set<Session> sessions = new HashSet<Session>();
        for (Serializable key : keys) {
            Session session = sessionCache.get(key);
            sessions.add(session);
        }
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);

        // 刷新内存相同逻辑
        this.update(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (null == sessionId) {
            logger.info("session or sessionId is null");
            return null;
        }
        RMapCache<Serializable, Session> sessionCache = redisson.getMapCache(sessionKeyPrefix);
        return sessionCache.get(sessionId.toString());
    }

    /**
     * 需要spring注入，所以public访问权限
     * 
     * @param sessionKeyPrefix
     */
    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }
}
