/**
 * Created on  13-10-28 23:42
 */
package com.alicp.jetcache.test.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 *
 * 自定义 Bean 的生成, 可以再次对bean做代理包装
 */
public class MyFactoryBean implements FactoryBean, InitializingBean {

    private FactoryBeanTarget target;

    public Object getObject() throws Exception {
        return target;
    }

    public Class<?> getObjectType() {
        return FactoryBeanTarget.class;
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * 初始化配置后调用
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        final FactoryBeanTarget SRC = new FactoryBeanTargetImpl();

        // 创建代理
        target = (FactoryBeanTarget) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class<?>[]{FactoryBeanTarget.class},
                (proxy, method, args) -> method.invoke(SRC, args));
    }
}
