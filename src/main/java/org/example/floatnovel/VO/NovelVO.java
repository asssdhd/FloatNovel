package org.example.floatnovel.VO;

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
public class NovelVO {
    private Long id;// 小说ID
    private String name;//小说名
    private String author;// 作者
    private String intro;// 简介
    private String cover;// 封面图URL
    private  Long category;//分类
    private LocalDateTime UpdateTime;//更新时间
    private LocalDateTime createTime;// 入库时间
    // ===== 排行相关字段 =====
    private Integer viewCount;      // 点击量
    private Integer collectCount;   // 收藏量

    private BigDecimal score;       // 平均评分
    private Integer scoreCount;     // 评分人数

    // ===== 最新章节 =====
    private String latestChapterTitle;       // 最新章节标题

    private List<Tag> tags;//小说标签
}
