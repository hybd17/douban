package com.experimental.douban.mapper;

import com.experimental.douban.entity.haveWatch;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HaveWatchMapper {
    Integer AddToHaveWatch(haveWatch haveWatch);
}
