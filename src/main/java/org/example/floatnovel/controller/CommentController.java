package org.example.floatnovel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
   private CommentService commentService;

   /* 第二阶段
    添加评论
            2025.10.16*/
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment){

        return commentService.add(comment);

    }

    /* 第二阶段
    获取评论列表
            2025.10.16*/
    @GetMapping("/list")
    public Result<Page<Comment>> list(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageNum") Integer pageSize
            ){

        Page<Comment> page = commentService.list(pageNum,pageSize);

        return Result.success(page);
    }

}
