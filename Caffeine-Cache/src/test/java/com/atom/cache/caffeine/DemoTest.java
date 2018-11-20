package com.atom.cache.caffeine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @program: cache-collection-demo
 * @description:
 * @author: Maxxx.Yg
 * @create: 2018-11-19 16:59
 **/
@SpringBootTest(classes = CaffeineApplication.class)
@RunWith(SpringRunner.class)
public class DemoTest {

    @Autowired
    private DemoService demoService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void test(){
        DemoEntity entity = new DemoEntity();
        entity.setId(222);
        entity.setName("hello 222");
        entity.setTime(new Date());
        demoService.save(entity);

        demoService.find(222);
        demoService.find(222);
        demoService.find(222);

        //Object obj = applicationContext.getBean(CacheManager.class);

        cacheManager.getCache("demo").get("222", ()->{
            // 如果缓存中没有则会调用此方法，常用于2级缓存
            return null;
        });

        demoService.remove(222);
    }

}
