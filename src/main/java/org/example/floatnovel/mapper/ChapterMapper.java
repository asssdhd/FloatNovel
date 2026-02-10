package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.DTO.CatalogueDTO;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Chapter;

import java.util.List;


@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {
    @Select("select id AS chapterId,title,orders from chapter where novel_id=#{novelId} order by orders ")
    List<CatalogueDTO> Catalogue(Long novelId);
    @Select("SELECT c.id AS chapterId, c.title, cc.content\n" +
            "FROM chapter c\n" +
            "JOIN chapter_content cc ON c.id = cc.chapter_id\n" +
            "WHERE c.id = #{chapterId}\n")
    ChapterDTO read(Long chapterId);
}
