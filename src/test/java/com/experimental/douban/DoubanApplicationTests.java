package com.experimental.douban;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.activation.DataSource;
import javax.annotation.Resource;

@SpringBootTest
class DoubanApplicationTests {
    @Resource
    private DataSource dataSource;


    @Test
    void contextLoads() {
    }
}
