package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.entity.NovelTag;
import org.example.floatnovel.entity.Tag;

import java.util.List;

public interface NovelTagMapper extends BaseMapper<NovelTag> {

    @Delete({"delete  from novel_tag where novel_id=#{novelId}"})
    void deleteByNovelId(Long novelId);

    @Select("SELECT t.id, t.name\n" +
            "FROM novel_tag nt\n" +
            "JOIN tag t ON nt.tag_id = t.id\n" +
            "WHERE nt.novel_id = #{novelId};")
    List<Tag> getTagByNovelId(Long id);
    @Delete("DELETE FROM novel_tag\n" +
            "WHERE novel_id = #{novelId}\n" +
            "  AND tag_id = #{tagId}; ")
    void deleteByNovelIdAndTagId(Long novelId, Long tagId);
}
