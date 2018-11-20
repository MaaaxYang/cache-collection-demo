package com.atom.cache.caffeine;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @program: cache-collection-demo
 * @description:
 * @author: Maxxx.Yg
 * @create: 2018-11-19 16:20
 **/
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CaffeineApplication {

    @Autowired
    private Gson gson;

    public static void main(String[] args){
        SpringApplication.run(CaffeineApplication.class);
    }


    //@Scheduled(cron = "0/1 * * * * * ?")
    public void test(){
        gson.toJson(new Object());
    }
}
