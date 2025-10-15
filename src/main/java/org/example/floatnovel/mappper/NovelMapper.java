package org.example.floatnovel.mappper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.floatnovel.entity.Novel;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {
}
