/**
 * RedisCache.java
 * Created at 2014年7月7日
 * Created by kkll
 */
package com.llsfw.core.security.session.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.security.session.SerializeUtils;

/**
 * <p>
 * ClassName: RedisCache
 * </p>
 * <p>
 * Description: redis缓存
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class RedisCache<K, V> implements Cache<K, V> {

    /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field cache: 数据管理
     * </p>
     */
    private DbManager cache;

    /**
     * <p>
     * Field keyPrefix: 键前缀
     * </p>
     */
    private String keyPrefix;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cache 数据管理
     * @param prefix 键前缀
     */
    public RedisCache(DbManager cache, String prefix) {
        this(cache);
        this.keyPrefix = prefix;
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cache 数据管理
     */
    public RedisCache(DbManager cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    /**
     * <p>
     * Description: 拼接完整的键
     * </p>
     * 
     * @param key 键
     * @return 键的字节数组
     */
    private byte[] getByteKey(K key) {
        if (key instanceof String) {
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        } else {
            return SerializeUtils.serialize(key);
        }
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("[cache]根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            } else {
                byte[] rawValue = cache.get(getByteKey(key));
                @SuppressWarnings("unchecked")
                V value = (V) SerializeUtils.deserialize(rawValue);
                return value;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("[cache]根据key从存储 key [" + key + "]");
        try {
            cache.set(getByteKey(key), SerializeUtils.serialize(value));
            return value;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("[cache]从redis中删除 key [" + key + "]");
        try {
            V previous = get(key);
            cache.del(getByteKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("[cache]从redis中删除所有元素");
        try {
            cache.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        try {
            Long longSize = new Long(cache.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        logger.debug("[cache]从redis中获取所有的key");
        try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            } else {
                Set<K> newKeys = new HashSet<K>();
                for (byte[] key : keys) {
                    newKeys.add((K) key);
                }
                return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        logger.debug("[cache]从redis中获取所有的values");
        try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
                    V value = get((K) key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

}
