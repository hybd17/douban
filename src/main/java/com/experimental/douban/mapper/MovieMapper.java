package com.experimental.douban.mapper;

import com.experimental.douban.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {
    Movie findByMovieName(String movieName);
}
