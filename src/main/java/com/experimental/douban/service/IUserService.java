package com.experimental.douban.service;

import com.experimental.douban.entity.User;
import com.experimental.douban.vo.HaveWatchVO;
import com.experimental.douban.vo.WantWatchVO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    //处理用户注册
    void reg(User user);
    User login(User user);
    void updatePassword(String oldPassword, String newPassword,Integer uid,String username);
    User findUserByUid(Integer uid);
    void userUpdateInfo(String phone,String email,Integer gender,String username,Integer uid);
    void userUpImage(String imageAddress,Integer uid);
    List<HaveWatchVO> getHVOByUid(Integer uid);
    List<WantWatchVO> getWVOByUid(Integer uid);
}
