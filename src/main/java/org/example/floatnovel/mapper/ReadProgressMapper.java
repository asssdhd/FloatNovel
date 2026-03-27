package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.floatnovel.VO.ReadProgressVO;
import org.example.floatnovel.entity.ReadProgress;

@Mapper
public interface ReadProgressMapper extends BaseMapper<ReadProgress> {

    @Select("select novel_id,chapter_id,offset from reading_progress where user_id=#{userId} and novel_id=#{novelId}")
    ReadProgressVO selectByUserIdAndNovelId(Long userId, Long novelId);

    @Insert("""
INSERT INTO reading_progress (user_id, novel_id, chapter_id, offset, update_time)
VALUES (#{userId}, #{novelId}, #{chapterId}, #{offset}, #{updateTime})
ON DUPLICATE KEY UPDATE
chapter_id = VALUES(chapter_id),
offset = VALUES(offset),
update_time = VALUES(update_time)
""")
    void saveReadProgress(ReadProgress readProgress);
}
