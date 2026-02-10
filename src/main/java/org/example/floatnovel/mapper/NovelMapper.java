package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.entity.Novel;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {


}
