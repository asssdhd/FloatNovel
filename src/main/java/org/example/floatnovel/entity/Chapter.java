package org.example.floatnovel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {

    private Long id;               // 章节ID
    private Long novelId;          // 小说ID
    private String title;          // 标题
    private LocalDateTime createTime; // 创建时间
    private Integer orders;   //章节排序

}
