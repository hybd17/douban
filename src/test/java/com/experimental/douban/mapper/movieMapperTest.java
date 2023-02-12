package com.experimental.douban.mapper;

import com.experimental.douban.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class movieMapperTest {
    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void findByMovieName(){
        Movie movie = movieMapper.findByMovieName("流浪地球");
        System.out.println(movie);
    }
}
