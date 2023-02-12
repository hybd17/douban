package com.experimental.douban.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class wantWatch extends BaseEntity{
    private Integer uid;
    private Integer mid;
    private String username;
    private String movieName;
    private Integer number;
}
