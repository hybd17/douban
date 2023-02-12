package com.experimental.douban.service;

import com.experimental.douban.entity.Movie;

public interface IMovieService {
    Movie findMovieByName(String MovieName);
}
