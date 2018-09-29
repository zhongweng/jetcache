package com.alicp.jetcache.support;

import java.util.function.Function;

/**
 * Created on 2016/10/4.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public abstract class AbstractValueEncoder implements Function<Object, byte[]> {

    protected boolean useIdentityNumber;

    protected void writeHeader(byte[] buf, int header) {
        /**
         * 4个字节 &0xFF 使前面都是0, 最后的1字节是本身
         * 这样当转为byte的时候直接截取的是最右的1字节
         * 最终将int 转存到buf[4] 中了 buf[0] 是最高1字节
         */
        buf[0] = (byte) (header >> 24 & 0xFF);
        buf[1] = (byte) (header >> 16 & 0xFF);
        buf[2] = (byte) (header >> 8 & 0xFF);
        buf[3] = (byte) (header & 0xFF);
    }

    public AbstractValueEncoder(boolean useIdentityNumber) {
        this.useIdentityNumber = useIdentityNumber;
    }

    public boolean isUseIdentityNumber() {
        return useIdentityNumber;
    }
}
