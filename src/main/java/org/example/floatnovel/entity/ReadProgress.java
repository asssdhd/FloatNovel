package org.example.floatnovel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("reading_progress")//mybatis-plus注解绑定表名
public class ReadProgress {

    private Long id;
    private Long userId;
    private Long novelId;
    private Long chapterId;
    private Long offset;
    private LocalDateTime updateTime;

}
