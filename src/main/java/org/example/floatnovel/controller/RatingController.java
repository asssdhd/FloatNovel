package org.example.floatnovel.controller;


import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mappper.NovelMapper;
import org.example.floatnovel.service.NovelService;
import org.example.floatnovel.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private NovelMapper novelMapper;


    /* 第三阶段
    添加评分
            2025.20.16*/
    @PostMapping("/add")
    public Result add(@RequestBody Rating rating) {

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
    public Result<Integer> score(@PathVariable Long novelId){

        return ratingService.getScore(novelId);

    }


}
