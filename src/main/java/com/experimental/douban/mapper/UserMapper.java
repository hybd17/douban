package com.experimental.douban.mapper;

import com.experimental.douban.entity.User;
import com.experimental.douban.vo.HaveWatchVO;
import com.experimental.douban.vo.WantWatchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    Integer addUser(User user);//注册新用户
    User findByUserName(String name);//主要判断注册用户是否存在
    Integer updatePassword(String username, String password, String modifiedUser, LocalDateTime modifiedTime);
    User findByUid(Integer uid);
    Integer updateInfoByUid(String phone,String email,Integer gender,String modifiedUser,LocalDateTime modifiedTime,Integer uid);
    Integer updateAvatarByUid(@Param("file") String ImageAddress,
                              String modifiedUser,
                              LocalDateTime modifiedTime,
                              Integer uid);
    List<HaveWatchVO> findHVObyUid(Integer uid);
    List<WantWatchVO> findWVObyUid(Integer uid);

}
