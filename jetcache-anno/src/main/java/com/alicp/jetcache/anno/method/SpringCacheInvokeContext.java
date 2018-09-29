/**
 * Created on  13-10-07 23:25
 */
package com.alicp.jetcache.anno.method;

import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 *
 * 这里就厉害了，就是Cache调用的上下文了, 用到的数据信息 基本上包含了
 */
public class SpringCacheInvokeContext extends CacheInvokeContext {
    protected ApplicationContext context;

    public SpringCacheInvokeContext(ApplicationContext context) {
        this.context = context;
    }

    public Object bean(String name) {
        return context.getBean(name);
    }


}
