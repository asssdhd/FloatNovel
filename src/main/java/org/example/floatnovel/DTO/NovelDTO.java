package org.example.floatnovel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.floatnovel.entity.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovelDTO {

    private Long id;// 小说ID
    private String name;//小说名
    private String author;// 作者
    private String intro;// 简介
    private String cover;// 封面图URL
    private  Long category;//分类
    private LocalDateTime UpdateTime;//更新时间
    private LocalDateTime createTime;// 入库时间
    private List<Long> tagIds;//小说标签ID集合

}
