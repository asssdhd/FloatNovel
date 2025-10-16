package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mappper.CommentMapper;
import org.example.floatnovel.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Service
public class CommentServiceimpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    /* 第二阶段
     添加评论
             2025.10.16*/
    @Override
    public Result add(Comment comment) {

        comment.setCreateTime(LocalDateTime.now());

        save(comment);

        return Result.success();
    }

    @Override
    public Page<Comment> list(Integer pageNum, Integer pageSize) {

        Page<Comment> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Comment::getCreateTime);
        return this.page(page,wrapper);

    }


}
