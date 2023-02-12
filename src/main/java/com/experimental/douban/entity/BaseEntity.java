package com.experimental.douban.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseEntity implements Serializable {
    private String createdUser;
    private LocalDateTime createdTime;
    private String modifiedUser;
    private LocalDateTime modifiedTime;
}
