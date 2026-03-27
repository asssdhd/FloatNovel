package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.example.floatnovel.entity.NovelTag;

public interface NovelTagMapper extends BaseMapper<NovelTag> {

    @Delete({"delete  from novel_tag where novel_id=#{novelId}"})
    void deleteByNovelId(Long novelId);
}
