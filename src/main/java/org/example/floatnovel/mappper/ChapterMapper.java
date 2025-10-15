package org.example.floatnovel.mappper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.floatnovel.entity.Chapter;
import org.springframework.stereotype.Service;


@Mapper
public interface ChapterMapper extends BaseMapper<Chapter> {

}
