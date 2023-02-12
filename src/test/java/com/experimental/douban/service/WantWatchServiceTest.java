package com.experimental.douban.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WantWatchServiceTest {
    @Resource
    private IWantWatchService wantWatchService;
    @Test
    public void insert(){
        Integer result = wantWatchService.addToWantWatch("李四", "流浪地球");
        System.out.println(result);
    }
}
