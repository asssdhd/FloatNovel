package org.example.floatnovel.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
@Tag(name = "评分模块")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private NovelMapper novelMapper;


    /* 第三阶段
    添加评分
            2025.20.16*/
    @PostMapping("/add")
    @Operation(summary = "添加评分接口")
    public Result add(@RequestBody Rating rating) {

        //判断小说是否存在
        Novel novel = novelMapper.selectById(rating.getNovelId());
        if (novel == null) {
            return Result.error("小说不存在，无法评分");
        }
       return ratingService.add(rating);

    }
    /* 第三阶段
    获取评分
            2025.20.16*/
    @GetMapping("/{novelId}")
    @Operation(summary = "获取评分接口")
    public Result<Integer> score(@PathVariable Long novelId){

        return ratingService.getScore(novelId);

    }


}
