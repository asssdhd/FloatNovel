package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.example.floatnovel.entity.ChapterContent;

public interface ChapterContentMapper extends BaseMapper<ChapterContent> {

    @Delete("delete from chapter_content where chapter_id = #{chapterId}")
    void deleteByChapterId(Long chapterId);
}
