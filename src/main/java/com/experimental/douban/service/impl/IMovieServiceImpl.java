package com.experimental.douban.service.impl;

import com.experimental.douban.entity.Movie;
import com.experimental.douban.entity.wantWatch;
import com.experimental.douban.mapper.MovieMapper;
import com.experimental.douban.mapper.UserMapper;
import com.experimental.douban.service.IMovieService;
import com.experimental.douban.service.ex.MovieNameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IMovieServiceImpl implements IMovieService {

    @Resource
    private MovieMapper movieMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Movie findMovieByName(String MovieName) {
        Movie movie = movieMapper.findByMovieName(MovieName);
        if(movie==null){
            throw new MovieNameNotFoundException("电影不存在");
        }
        if(movie.getPointNumber()<100){
            movie.setPoint(null);
        }
        return movie;
    }
}
