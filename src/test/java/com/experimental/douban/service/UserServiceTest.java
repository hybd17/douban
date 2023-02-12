package com.experimental.douban.service;

import com.experimental.douban.entity.User;
import com.experimental.douban.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private IUserService iUserService;

    @Test
    public void reg(){
        try{
            User user = new User();
            user.setUsername("李四");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("运行成功");
        }catch (ServiceException e){
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user1 = new User();
        user1.setUsername("李四");
        user1.setPassword("123456");
        User user = iUserService.login(user1);
        System.out.println(user);
    }
    @Test
    public void updatePassword(){
        iUserService.updatePassword("123456","456789",3,"Mary");
    }
    @Test
    public void findByUid(){
        System.err.println(iUserService.findUserByUid(1).getUsername());
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setPhone("3785412");
        user.setEmail("46598@qq.com");
        user.setGender(0);
        user.setUid(1);
        user.setUsername("李四");
        iUserService.userUpdateInfo(user.getPhone(), user.getEmail(), user.getGender(), user.getUsername(), user.getUid());
    }
}
