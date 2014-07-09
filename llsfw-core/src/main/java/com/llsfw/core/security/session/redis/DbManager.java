/**
 * DbManager.java
 * Created at 2014年7月7日
 * Created by kkll
 */
package com.llsfw.core.security.session.redis;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * ClassName: DbManager
 * </p>
 * <p>
 * Description: redis存储
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class DbManager {

    /**
     * <p>
     * Field redisTemplate: redis操作
     * </p>
     */
    private RedisTemplate<byte[], byte[]> redisTemplate;

    public RedisTemplate<byte[], byte[]> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<byte[], byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * <p>
     * Description: 删除
     * </p>
     * 
     * @param key 键
     * @return 结果
     */
    public long del(final byte[] key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(key);
            }
        });
    }

    /**
     * <p>
     * Description: 存储
     * </p>
     * 
     * @param key 键
     * @param value 值
     * @param liveTime 超时时间
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    //    public void set(String key, String value, long liveTime) {
    //        this.set(key.getBytes(), value.getBytes(), liveTime);
    //    }

    //    public void set(String key, String value) {
    //        this.set(key, value, 0L);
    //    }

    /**
     * <p>
     * Description: 存储
     * </p>
     * 
     * @param key 键
     * @param value 值
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * <p>
     * Description: 根据键获得值
     * </p>
     * 
     * @param key 键
     * @return 值
     */
    public byte[] get(final byte[] key) {
        return redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key);
            }
        });
    }

    /**
     * <p>
     * Description: 询所有的键
     * </p>
     * 
     * @param pattern 条件
     * @return 键集合
     */
    public Set<byte[]> keys(String pattern) {
        return redisTemplate.keys(pattern.getBytes());

    }

    /**
     * <p>
     * Description: 判断key是否存在
     * </p>
     * 
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * <p>
     * Description: 刷新数据
     * </p>
     * 
     * @return 结果
     */
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * <p>
     * Description: 返回数据大小
     * </p>
     * 
     * @return 数据大小
     */
    public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * <p>
     * Description: ping链接
     * </p>
     * 
     * @return 结果
     */
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }
}
