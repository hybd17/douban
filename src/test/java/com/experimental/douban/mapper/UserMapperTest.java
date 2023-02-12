package com.experimental.douban.mapper;

import com.experimental.douban.entity.User;
import com.experimental.douban.vo.HaveWatchVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        Integer row1 = userMapper.addUser(user);
        System.out.println(row1);
    }
    @Test
    public void findByUserName(){
        User user = userMapper.findByUserName("张三");
        System.out.println(user);
    }
    @Test
    public void updatePassword(){
        userMapper.updatePassword("Jerry","456789","管理员", LocalDateTime.now());
    }
    @Test
    public void findByUid(){
        User user = userMapper.findByUid(4);
        System.out.println(user);
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(1);
        user.setPhone("123456789");
        user.setEmail("1546@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user.getPhone(), user.getEmail(), user.getGender(), user.getUsername(), LocalDateTime.now(), user.getUid());
    }
    @Test
    public void updateAvatar(){
        userMapper.updateAvatarByUid("abc","管理员",LocalDateTime.now(),3);
    }
    @Test
    public void findHVObyUid(){
        Integer uid = 1;
        List<HaveWatchVO> hvObyUid = userMapper.findHVObyUid(uid);
        for (HaveWatchVO ha :
                hvObyUid) {
            System.out.println(ha);
        }
    }
}
