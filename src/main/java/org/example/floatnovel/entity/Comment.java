package org.example.floatnovel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;

    private  Long userId;

    private Long rootId;

    private Long parent_id;

    private String content;

    private LocalDateTime createTime;

}
