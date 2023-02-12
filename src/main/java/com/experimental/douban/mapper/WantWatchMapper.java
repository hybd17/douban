package com.experimental.douban.mapper;

import com.experimental.douban.entity.wantWatch;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WantWatchMapper {
    Integer InsertWantWatch(wantWatch wantWatch);
}
