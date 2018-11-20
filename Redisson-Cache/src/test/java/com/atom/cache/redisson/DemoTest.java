package com.atom.cache.redisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: cache-collection-demo
 * @description:
 * @author: Maxxx.Yg
 * @create: 2018-11-19 17:53
 **/
@SpringBootTest(classes = RedissonApplication.class)
@RunWith(SpringRunner.class)
public class DemoTest {



    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() throws InterruptedException {

        String key = "testKey";

        // obj
        redissonClient.getBucket(key).set("testValue");

        redissonClient.getBucket(key).set("timeOutValue",100,TimeUnit.SECONDS);

        Object object = redissonClient.getBucket(key).get();

        // list
        redissonClient.getList("testList").add("list-1");

        List<Object> list = redissonClient.getList("testList").readAll();

        // set
        redissonClient.getSet("testSet").add("set-1");

        redissonClient.getSet("testSet").expire(1000,TimeUnit.SECONDS);

        Set<Object> set = redissonClient.getSet("testSet").readAll();

        // map
        redissonClient.getMap("testMap").put("testKey","testValue");

        Object mapVal = redissonClient.getMap("testMap").get("testKey");

        // lock
        RLock lock = redissonClient.getLock("testLock");
        lock.lock(10,TimeUnit.SECONDS);

        //Thread.sleep(1000*10);

        lock.unlock();


        // long and double atomic
        Long longVal = redissonClient.getAtomicLong("testLong").incrementAndGet();
        Double doubleVal = redissonClient.getAtomicDouble("testDouble").incrementAndGet();

        // 缓存监听事件(这个只限于本地使用)，一般用来与Caffeine 配合使用二级缓存
        // https://blog.csdn.net/lovejj1994/article/details/83148284
        redissonClient.getTopic("testTopic").addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence channel, String msg) {
                System.out.println(msg);
            }
        });


        redissonClient.getTopic("testTopic").publish("delete");

        System.out.println("end");
    }

}
