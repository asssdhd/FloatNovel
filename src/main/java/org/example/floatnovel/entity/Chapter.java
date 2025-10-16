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
    private String content;        // 章节内容URL
    private LocalDateTime createTime; // 上传时间
    private Integer orders;
}
