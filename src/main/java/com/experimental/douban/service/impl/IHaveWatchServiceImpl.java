package com.experimental.douban.service.impl;

import com.experimental.douban.entity.Movie;
import com.experimental.douban.entity.User;
import com.experimental.douban.entity.haveWatch;
import com.experimental.douban.mapper.HaveWatchMapper;
import com.experimental.douban.mapper.MovieMapper;
import com.experimental.douban.mapper.UserMapper;
import com.experimental.douban.service.IHaveWatchService;
import com.experimental.douban.service.ex.InsertException;
import com.experimental.douban.service.ex.MovieNameNotFoundException;
import com.experimental.douban.service.ex.UserNameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class IHaveWatchServiceImpl implements IHaveWatchService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MovieMapper movieMapper;
    @Resource
    private HaveWatchMapper haveWatchMapper;
    @Override
    public Integer addToHaveWatch(String username, String movieName) {
        haveWatch haveWatch = new haveWatch();
        User user = userMapper.findByUserName(username);
        if(user==null||user.getIsDelete()==1||user.getIsDefault()==1){
            throw new UserNameNotFoundException("用户不存在");
        }
        Movie movie = movieMapper.findByMovieName(movieName);
        if(movie==null){
            throw new MovieNameNotFoundException("电影不存在");
        }
        haveWatch.setUid(user.getUid());
        haveWatch.setMid(movieMapper.findByMovieName(movieName).getMid());
        haveWatch.setUsername(username);
        haveWatch.setMovieName(movieName);
        haveWatch.setModifiedTime(LocalDateTime.now());
        haveWatch.setModifiedUser(username);
        haveWatch.setCreatedTime(LocalDateTime.now());
        haveWatch.setCreatedUser(username);
        Integer result = haveWatchMapper.AddToHaveWatch(haveWatch);
        if(result==0){
            throw new InsertException("添加失败");
        }
        return result;
    }
}
