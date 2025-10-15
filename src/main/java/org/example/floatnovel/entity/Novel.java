package org.example.floatnovel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Novel {

    private Long id;// 小说ID
    private String name;//小说名
    private String author;// 作者
    private String intro;// 简介
    private String cover;// 封面图URL
    private LocalDateTime createTime;// 入库时间
}
