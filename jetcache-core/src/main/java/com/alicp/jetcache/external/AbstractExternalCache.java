package com.alicp.jetcache.external;

import com.alicp.jetcache.AbstractCache;
import com.alicp.jetcache.CacheConfigException;
import com.alicp.jetcache.CacheException;

import java.io.IOException;

/**
 * Created on 2016/10/8.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 *
 * 与嵌入的cache的区别：嵌入的都是本地缓存，而externalCache的是远程缓存
 * 则需要 编码和解码器
 */
public abstract class AbstractExternalCache<K, V> extends AbstractCache<K, V> {

    private ExternalCacheConfig<K, V> config;

    public AbstractExternalCache(ExternalCacheConfig<K, V> config) {
        this.config = config;
        checkConfig();
    }

    protected void checkConfig() {
        if (config.getValueEncoder() == null) {
            throw new CacheConfigException("no value encoder");
        }
        if (config.getValueDecoder() == null) {
            throw new CacheConfigException("no value decoder");
        }
        if (config.getKeyPrefix() == null){
            throw new CacheConfigException("keyPrefix is required");
        }
    }

    public byte[] buildKey(K key) {
        try {
            Object newKey = key;
            if (key instanceof byte[]) {
                newKey = key;
            } else if(key instanceof String){
                newKey = key;
            } else {
                if (config.getKeyConvertor() != null) {
                    newKey = config.getKeyConvertor().apply(key);
                }
            }
            // 创建带前缀的key
            return ExternalKeyUtil.buildKeyAfterConvert(newKey, config.getKeyPrefix());
        } catch (IOException e) {
            throw new CacheException(e);
        }
    }

}
