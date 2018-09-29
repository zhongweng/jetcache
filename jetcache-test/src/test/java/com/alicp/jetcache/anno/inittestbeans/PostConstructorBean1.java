package com.alicp.jetcache.anno.inittestbeans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created on 2017/5/4.
 *
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Component
public class PostConstructorBean1 extends MethodCacheInitTestBean {

    @PostConstruct
    public void doTest() {
        System.out.println(">>>PostConstructorBean1");
        super.doTest();
        System.out.println("<<<PostConstructorBean1");
    }


}
