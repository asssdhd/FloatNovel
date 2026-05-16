package org.example.floatnovel.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Tag(name = "评论模块")
public class CommentController {

    @Autowired
   private CommentService commentService;

   /* 第二阶段
    添加评论
            2025.10.16*/
    @PostMapping("/add")
    @Operation(summary = "添加评论接口")
    public Result add(@RequestBody Comment comment){

        return commentService.add(comment);

    }

    /* 第二阶段
    获取评论列表
            2025.10.16*/
    @GetMapping("/list")
    @Operation(summary = "获取评论接口")
    public Result<Page<Comment>> list(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
            ){

        System.out.println("评论列表controller");
        Page<Comment> page = commentService.list(pageNum,pageSize);

        return Result.success(page);
    }

    /*获取小说相关评论*/
    @GetMapping("/{novelId}")
    @Operation(summary = "获取小说评论接口")
    public Result<List<Comment>> get(@PathVariable("novelId") Long novelId){

        return commentService.getByNovelId(novelId);
    }

    /*TODO
    *  删除评论*/



    /*
    * 我的评论
    * */
    @GetMapping()
    @Operation(summary = "我的评论")
    public Result<List<Comment>> getByNovelId(){
        return commentService.MyComment();
    }






}
