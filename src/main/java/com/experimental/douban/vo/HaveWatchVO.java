package com.experimental.douban.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HaveWatchVO implements Serializable {
    private Integer uid;
    private Integer mid;
    private String username;
    private String movieName;
    private String avatar;
}
