package org.example.floatnovel.mappper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.entity.Rating;

@Mapper
public interface RatingMapper extends BaseMapper<Rating> {

    @Select("select score from rating where novel_id=#{novelId}")
    Integer selectByNovelId(Long novelId);
}
