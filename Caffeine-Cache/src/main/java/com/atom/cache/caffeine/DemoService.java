package com.atom.cache.caffeine;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: cache-collection-demo
 * @description:
 * @author: Maxxx.Yg
 * @create: 2018-11-19 17:00
 **/
@Component
@Slf4j
public class DemoService {

    private static ConcurrentHashMap<Integer,DemoEntity> map = new ConcurrentHashMap<>();

    @Autowired
    private Gson gson;

    @Autowired
    private CaffeineCacheManager caffeineCacheManager;

    @CachePut(value = "demo",key = "#entity.id")
    public DemoEntity save(DemoEntity entity){
        log.info("保存了：{}",gson.toJson(entity));
        map.put(entity.getId(),entity);
        return entity;
    }


    @CacheEvict(value = "demo")
    public void remove(Integer id){
        log.info("删除了id：{}",id);
        map.remove(id);
    }


    @Cacheable(value = "demo",key = "#id")
    public DemoEntity find(Integer id){
        DemoEntity demoEntity = map.get(id);
        log.info("读取内容:{}",gson.toJson(demoEntity));
        return demoEntity;
    }

    public void test(){
        caffeineCacheManager.getCache("demo");
    }
}
