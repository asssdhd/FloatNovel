package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.RatingMapper;
import org.example.floatnovel.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        long userId = StpUtil.getLoginIdAsLong();//从Sa-Token中获取用户ID

        rating.setUserId(userId);//设置用户ID
        rating.setCreateTime(LocalDate.now());//设置创建时间

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
