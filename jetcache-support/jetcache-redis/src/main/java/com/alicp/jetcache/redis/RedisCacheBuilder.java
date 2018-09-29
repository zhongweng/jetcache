package com.alicp.jetcache.redis;

import com.alicp.jetcache.external.ExternalCacheBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

/**
 * Created on 2016/10/7.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public class RedisCacheBuilder<T extends ExternalCacheBuilder<T>> extends ExternalCacheBuilder<T> {

    /**
     * 单例模式也用内部类创建的手段，但是这里没有用来实现单例
     */
    public static class RedisCacheBuilderImpl extends RedisCacheBuilder<RedisCacheBuilderImpl> {
    }

    public static RedisCacheBuilderImpl createRedisCacheBuilder() {
        return new RedisCacheBuilderImpl();
    }

    /**
     * 设为protected 的，不可自由new，只能通过createRedisCacheBuilder 创建
     */
    protected RedisCacheBuilder() {

        /**
         * 此处设置了config和cache关联关系
         */
        buildFunc(config -> new RedisCache((RedisCacheConfig) config));
    }

    @Override
    public RedisCacheConfig getConfig() {
        if (config == null) {
            config = new RedisCacheConfig();
        }
        return (RedisCacheConfig) config;
    }

    public T jedisPool(Pool<Jedis> pool){
        /**
         * 这里给config设置了pool
         */
        getConfig().setJedisPool(pool);
        return self();
    }

    public void setJedisPool(JedisPool jedisPool) {
        getConfig().setJedisPool(jedisPool);
    }

}
