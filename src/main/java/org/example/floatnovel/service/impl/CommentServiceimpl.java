package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.CommentMapper;
import org.example.floatnovel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceimpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private CommentMapper commentMapper;
    /* 第二阶段
     添加评论
             2025.10.16*/
    @Override
    public Result add(Comment comment) {


        long userId = StpUtil.getLoginIdAsLong();

        comment.setUserId(userId);
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

    /*根据小说id获取评论*/
    public Result<List<Comment>> getByNovelId(Long novelId) {

        List<Comment> commentList=commentMapper.selectByNovelId(novelId);
        return Result.success(commentList);
    }


}
