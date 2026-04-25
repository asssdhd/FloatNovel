package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.floatnovel.VO.BookshelfVO;
import org.example.floatnovel.entity.Bookshelf;

import java.util.List;

@Mapper
public interface BookshelfMapper extends BaseMapper<Bookshelf> {


    /*
   删除书架上的小说
   2025.12.12
    */
    @Update({"<script>",
            "UPDATE bookshelf SET is_deleted = 1 WHERE novel_id IN",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    void deleteBYids(List<Long> ids);

    @Select("select novel_id,novel_name,cover from bookshelf where user_id=#{userId} and is_deleted = 0")
    List<BookshelfVO> getAll(Long userId);

    @Update("update bookshelf set is_deleted = 0 where user_id = #{userId} and novel_id=#{novelId}")
    int recover(Long userId, Long novelId);
}
