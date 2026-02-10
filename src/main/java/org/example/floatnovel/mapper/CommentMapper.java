package org.example.floatnovel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.floatnovel.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
