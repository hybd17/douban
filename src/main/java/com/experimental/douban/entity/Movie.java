package com.experimental.douban.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie extends BaseEntity{
    private Integer mid;
    private String movieName;
    private Double point;
    private Double pointHide;
    private Integer pointNumber;
    private String director;
    private String pubDate;
    private String avatar;
    private String evaluate;
    private String briefIntroduction;
    private String pictures;
    private String actors;
}
