package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.floatnovel.entity.Novel;

import java.util.List;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {

    @Update("update novel set view_count=view_count + 1 where id =#{novelId}")
    void UpdateViewCountOne(Long novelId);

    @Select("select * from novel where author = #{author}")
    List<Novel> selectByAuthorName(String author);

    @Select("select * from novel ")
    List<Novel> getAll();
}
