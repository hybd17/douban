package com.experimental.douban.service;

import com.experimental.douban.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieServiceTest {
    @Resource
    private IMovieService movieService;

    @Test
    public void findMovieByName(){
        System.out.println(movieService.findMovieByName("流浪地球"));
    }
}
