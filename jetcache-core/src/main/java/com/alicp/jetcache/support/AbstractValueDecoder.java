package com.alicp.jetcache.support;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created on 2016/10/4.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public abstract class AbstractValueDecoder implements Function<byte[], Object> {

    protected boolean useIdentityNumber;

    public AbstractValueDecoder(boolean useIdentityNumber) {
        this.useIdentityNumber = useIdentityNumber;
    }

    /**
     * 将byte[] 转为int
     * @param buf
     * @return
     */
    protected int parseHeader(byte[] buf) {
        int x = 0;
        x = x | (buf[0] & 0xFF);
        x <<= 8;
        x = x | (buf[1] & 0xFF);
        x <<= 8;
        x = x | (buf[2] & 0xFF);
        x <<= 8;
        x = x | (buf[3] & 0xFF);
        return x;
    }

    protected abstract Object doApply(byte[] buffer) throws Exception;

    @Override
    public Object apply(byte[] buffer) {
        try {
            if (useIdentityNumber) {
                DecoderMap.registerBuildInDecoder();
                /**
                 * 解析数据流头部
                 */
                int identityNumber = parseHeader(buffer);

                /**
                 * 根据数据流头部编号 获取对应解析器
                 */
                AbstractValueDecoder decoder = DecoderMap.getDecoder(identityNumber);

                Objects.requireNonNull(decoder, "no decoder for identity number:" + identityNumber);
                /**
                 * 解析获得对象
                 */
                return decoder.doApply(buffer);
            } else {
                return doApply(buffer);
            }
        } catch (Exception e) {
            throw new CacheEncodeException("decode error", e);
        }
    }

    public boolean isUseIdentityNumber() {
        return useIdentityNumber;
    }
}
