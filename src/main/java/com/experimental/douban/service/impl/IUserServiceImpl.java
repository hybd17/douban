package com.experimental.douban.service.impl;

import com.experimental.douban.entity.User;
import com.experimental.douban.mapper.UserMapper;
import com.experimental.douban.service.IUserService;
import com.experimental.douban.service.ex.InsertException;
import com.experimental.douban.service.ex.PasswordWrongException;
import com.experimental.douban.service.ex.UserNameNotFoundException;
import com.experimental.douban.service.ex.UserNameRepeated;
import com.experimental.douban.vo.HaveWatchVO;
import com.experimental.douban.vo.WantWatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String userName = user.getUsername();
        User result = userMapper.findByUserName(userName);
        if(result != null)
        {
            throw new UserNameRepeated("用户名已被占用");
        }
        //密码加密处理
        String primaryPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String password = getPassword(primaryPassword,salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setIsDelete(0);
        user.setIsDefault(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setCreatedTime(LocalDateTime.now());
        user.setModifiedTime(LocalDateTime.now());
        //执行注册
        Integer row = userMapper.addUser(user);
        if(row!=1){
            throw new InsertException("注册过程中产生了服务器宕机或其他未知异常");
        }

    }

    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User loginUser = userMapper.findByUserName(username);//loginUser为实际数据库中数据，进行验证用户
        if(loginUser == null){
            throw new UserNameNotFoundException("用户不存在");
        }
        String primaryPassword = loginUser.getPassword();
        String salt = loginUser.getSalt();
        Integer deleteStatus = loginUser.getIsDelete();
        Integer defaultStatus = loginUser.getIsDefault();
        String MD5Password = getPassword(password,salt);
        if(!primaryPassword.equals(MD5Password)){
            throw new PasswordWrongException("您输入的密码错误");
        }
        if(defaultStatus ==1){
            throw new UserNameNotFoundException("用户账号已被封禁");
        }
        if(deleteStatus==1){
            throw new UserNameNotFoundException("用户已注销账号");
        }
        //创建一个新的对象，传输数据只用于部分用途，减少传输量，提高传输速度
        User temporaryUser = new User();
        temporaryUser.setUid(loginUser.getUid());
        temporaryUser.setUsername(loginUser.getUsername());
        temporaryUser.setAvatar(loginUser.getAvatar());
        return temporaryUser;
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, Integer uid,String username) {
        User user = userMapper.findByUid(uid);
        if(user==null || user.getIsDefault()==1 || user.getIsDelete()==1 ){
            throw new UserNameNotFoundException("用户不存在");
        }
        String salt = user.getSalt();
        String primaryPassword = user.getPassword();
        String md5Password = getPassword(oldPassword,salt);
        if(!primaryPassword.equals(md5Password)){
            throw new PasswordWrongException("密码不正确");
        }
        String newSalt = UUID.randomUUID().toString().toUpperCase();
        String newMD5Password = getPassword(newPassword,newSalt);
        user.setSalt(newSalt);
        Integer result = userMapper.updatePassword(username, newMD5Password, user.getUsername(), LocalDateTime.now());
        if(result!=1){
            throw new InsertException("密码修改失败");
        }
    }

    @Override
    public User findUserByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDefault()==1 || result.getIsDelete()==1){
            throw new UserNameNotFoundException("用户不存在");
        }
        return result;
    }

    @Override
    public void userUpdateInfo(String phone, String email, Integer gender, String username, Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user==null||user.getIsDefault()==1 || user.getIsDelete()==1){
            throw new UserNameNotFoundException("用户不存在");
        }
        Integer result = userMapper.updateInfoByUid(phone, email, gender, username, LocalDateTime.now(), uid);
        if(result==0){
            throw new InsertException("数据更新失败");
        }
    }

    @Override
    public void userUpImage(String imageAddress, Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user==null||user.getIsDefault()==1 || user.getIsDelete()==1){
            throw new UserNameNotFoundException("用户不存在");
        }
        Integer result = userMapper.updateAvatarByUid(imageAddress, user.getUsername(), LocalDateTime.now(), uid);
        if(result==0){
            throw new InsertException("图片上传失败");
        }
    }

    @Override
    public List<HaveWatchVO> getHVOByUid(Integer uid) {
        List<HaveWatchVO> haveWatchVOS = userMapper.findHVObyUid(uid);
        return haveWatchVOS;
    }

    @Override
    public List<WantWatchVO> getWVOByUid(Integer uid) {
        List<WantWatchVO> wantWatchVOS = userMapper.findWVObyUid(uid);
        return wantWatchVOS;
    }

    private String getPassword(String password, String salt){
        for(int i = 0;i<3;i++)
        {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
