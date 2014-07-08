/**
 * RedisSessionDAO.java
 * Created at 2014年7月7日
 * Created by kkll
 */
package com.llsfw.core.security.session.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.security.session.SerializeUtils;

/**
 * <p>
 * ClassName: RedisSessionDAO
 * </p>
 * <p>
 * Description: redisSessionDao
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    /**
     * <p>
     * Field keyPrefix: 键前缀
     * </p>
     */
    private String keyPrefix;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * <p>
     * Field dbManager: 数据管理
     * </p>
     */
    private DbManager dbManager;

    public DbManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * <p>
     * Description: 拼接完整的键
     * </p>
     * 
     * @param sessionId 会话ID
     * @return 键的字节数组
     */
    public byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    /**
     * <p>
     * Description: 存储会话
     * </p>
     * 
     * @param session 会话
     * @throws UnknownSessionException 异常
     */
    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        this.dbManager.set(key, value, session.getTimeout() / 1000);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.debug("更新session");
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        logger.debug("删除session");
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return;
        }
        this.dbManager.del(getByteKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.debug("获取正在活动中的session列表");
        Set<Session> sessions = new HashSet<Session>();
        Set<byte[]> keys = dbManager.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (byte[] key : keys) {
                Session s = (Session) SerializeUtils.deserialize(dbManager.get(key));
                sessions.add(s);
            }
        }
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        logger.debug("创建session");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("读取session");
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
        Session s = (Session) SerializeUtils.deserialize(dbManager.get(getByteKey(sessionId)));
        return s;
    }
}
