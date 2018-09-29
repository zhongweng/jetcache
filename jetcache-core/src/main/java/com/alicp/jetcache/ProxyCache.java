package com.alicp.jetcache;

/**
 * Created on 2016/12/13.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public interface ProxyCache<K, V> extends Cache<K, V> {
    Cache<K, V> getTargetCache();

    /**
     * 脱去外衣
     * @param clazz the proprietary class or interface of the underlying
     *              concrete cache. It is this type that is returned.
     * @param <T>
     * @return
     */
    @Override
    default <T> T unwrap(Class<T> clazz) {
        return getTargetCache().unwrap(clazz);
    }

}
