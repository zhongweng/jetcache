package com.alicp.jetcache.external;

import com.alicp.jetcache.CacheConfig;
import com.alicp.jetcache.support.JavaValueEncoder;
import com.alicp.jetcache.support.SpringJavaValueDecoder;

import java.util.function.Function;

/**
 * Created on 16/9/9.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public class ExternalCacheConfig<K, V> extends CacheConfig<K, V> {
    private String keyPrefix;
    /**
     * 默认编码器 Object to byte[]
     */
    private Function<Object, byte[]> valueEncoder = JavaValueEncoder.INSTANCE;
    /**
     * 默认解码器 byte[] to Object
     */
    private Function<byte[], Object> valueDecoder = SpringJavaValueDecoder.defaultJavaValueDecoder();

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Function<Object, byte[]> getValueEncoder() {
        return valueEncoder;
    }

    public void setValueEncoder(Function<Object, byte[]> valueEncoder) {
        this.valueEncoder = valueEncoder;
    }

    public Function<byte[], Object> getValueDecoder() {
        return valueDecoder;
    }

    public void setValueDecoder(Function<byte[], Object> valueDecoder) {
        this.valueDecoder = valueDecoder;
    }
}
