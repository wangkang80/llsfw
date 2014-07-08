/**
 * RedisCacheManager.java
 * Created at 2014年7月7日
 * Created by kkll
 */
package com.llsfw.core.security.session.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: RedisCacheManager
 * </p>
 * <p>
 * Description: RedisCacheManager
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class RedisCacheManager implements CacheManager {

    /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

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

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param dbManager 数据管理
     */
    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        return new RedisCache<K, V>(dbManager, keyPrefix);
    }

}
