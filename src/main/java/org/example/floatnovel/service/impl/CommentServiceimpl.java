package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.config.RabbitMQConfig;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.CommentMapper;
import org.example.floatnovel.service.CommentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceimpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /* 第二阶段
     添加评论
             2025.10.16*/
    @Override
    public Result add(Comment comment) {


        long userId = StpUtil.getLoginIdAsLong();

        comment.setUserId(userId);
        comment.setCreateTime(LocalDateTime.now());

        save(comment);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, comment.getContent());

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

  /*
  * 我的评论
  * */
    public Result<List<Comment>> MyComment() {

        //获取用户ID
        long userId = StpUtil.getLoginIdAsLong();

        //根据ID查询评论
        List<Comment> commentList = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getUserId, userId));

        return Result.success(commentList);
    }


}
