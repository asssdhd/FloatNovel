package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mappper.RatingMapper;
import org.example.floatnovel.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Service
public class RatingServiceimpl extends ServiceImpl<RatingMapper, Rating> implements RatingService {

    @Autowired
    private RatingMapper ratingMapper;

    /* 第三阶段
     添加评分
             2025.20.16*/
    @Override
    public Result add(Rating rating) {

        rating.setCreateTime(LocalDate.now());

        save(rating);

        return Result.success();
    }


    /* 第三阶段
   获取评分
           2025.20.16*/
    @Override
    public Result<Integer> getScore(Long novelId) {

        Integer Socre=ratingMapper.selectByNovelId(novelId);

        return Result.success(Socre);
    }


}
