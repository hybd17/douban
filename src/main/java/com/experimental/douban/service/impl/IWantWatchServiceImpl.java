package com.experimental.douban.service.impl;

import com.experimental.douban.entity.Movie;
import com.experimental.douban.entity.User;
import com.experimental.douban.entity.wantWatch;
import com.experimental.douban.mapper.MovieMapper;
import com.experimental.douban.mapper.UserMapper;
import com.experimental.douban.mapper.WantWatchMapper;
import com.experimental.douban.service.IWantWatchService;
import com.experimental.douban.service.ex.InsertException;
import com.experimental.douban.service.ex.MovieNameNotFoundException;
import com.experimental.douban.service.ex.UserNameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class IWantWatchServiceImpl implements IWantWatchService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MovieMapper movieMapper;
    @Resource
    private WantWatchMapper wantWatchMapper;

    @Override
    public Integer addToWantWatch(String username,String movieName) {
        wantWatch wantWatch = new wantWatch();
        User user = userMapper.findByUserName(username);
        if(user==null||user.getIsDelete()==1||user.getIsDefault()==1){
            throw new UserNameNotFoundException("用户不存在");
        }
        Movie movie = movieMapper.findByMovieName(movieName);
        if(movie==null){
            throw new MovieNameNotFoundException("电影不存在");
        }
        wantWatch.setUid(user.getUid());
        wantWatch.setMid(movieMapper.findByMovieName(movieName).getMid());
        wantWatch.setUsername(username);
        wantWatch.setMovieName(movieName);
        wantWatch.setModifiedTime(LocalDateTime.now());
        wantWatch.setModifiedUser(username);
        wantWatch.setCreatedTime(LocalDateTime.now());
        wantWatch.setCreatedUser(username);
        Integer result = wantWatchMapper.InsertWantWatch(wantWatch);
        if(result==0){
            throw new InsertException("添加失败");
        }
        return result;
    }
}
