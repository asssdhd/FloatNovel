package org.example.floatnovel.Task;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.constant.RedisKey;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.mapper.ReadProgressMapper;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.floatnovel.constant.RedisKey.SCORE_RANK_KEY;

@Slf4j
@Component
public class RankTask {

    @Autowired
    private NovelService novelService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void RankTask(){

        //清理旧的排行榜
        stringRedisTemplate.delete(RedisKey.VIEW_RANK_KEY);
        stringRedisTemplate.delete(RedisKey.COLLECT_RANK_KEY);
        stringRedisTemplate.delete(RedisKey.SCORE_RANK_KEY);

        //从mysql获取全部小说
       List<Novel> novels= novelService.getAll();
       log.info("全部小说{}",novels);

       //根据浏览量排序
      List<Novel> viewSet = novels.stream()
                .sorted(Comparator.comparing(Novel::getViewCount).reversed()).collect(Collectors.toList());
        log.info("根据浏览量排序得:{}",viewSet);
        //保存进redis中
        viewSet.stream().limit(100).forEach(novel -> {


            String novelId = novel.getId().toString();

            //保存zset
            stringRedisTemplate.opsForZSet().add(
                    RedisKey.VIEW_RANK_KEY,
                    novelId,
                    novel.getViewCount()
            );



            //保存hash
            // ✅ 2. 写入详情（Hash）
            String json = JSON.toJSONString(novel);

            stringRedisTemplate.opsForHash().put(
                    RedisKey.VIEW_INFO_KEY,
                    novelId,
                    json
            );

        });

        //根据收藏量排序
        List<Novel> collectSet = novels.stream()
                .sorted(Comparator.comparing(Novel::getCollectCount).reversed())
                .collect(Collectors.toList());
        log.info("根据收藏量排序得:{}",collectSet);
        //保存进redis中
        //保存进redis中
        collectSet.stream().limit(100).forEach(novel -> {


            String novelId = novel.getId().toString();

            //保存zset
            stringRedisTemplate.opsForZSet().add(
                    RedisKey.COLLECT_RANK_KEY,
                    novelId,
                    novel.getCollectCount()
            );



            //保存hash
            // ✅ 2. 写入详情（Hash）
            String json = JSON.toJSONString(novel);

            stringRedisTemplate.opsForHash().put(
                    RedisKey.COLLECT_INFO_KEY,
                    novelId,
                    json
            );

        });

        //根据评分排序
      List<Novel> ScoreSet = novels.stream()
                .sorted(Comparator.comparing(Novel::getScore).reversed())
                .collect(Collectors.toList());
        log.info("根据评分排序:{}",ScoreSet);
        //保存进redis中
        //保存进redis中
        ScoreSet.stream().limit(100).forEach(novel -> {


            String novelId = novel.getId().toString();

            double score = novel.getScore() == null ? 0 : novel.getScore().doubleValue();
            //保存zset
            stringRedisTemplate.opsForZSet().add(
                    RedisKey.SCORE_RANK_KEY,
                    novelId,
                    score
            );



            //保存hash
            // ✅ 2. 写入详情（Hash）
            String json = JSON.toJSONString(novel);

            stringRedisTemplate.opsForHash().put(
                    RedisKey.SCORE_INFO_KEY,
                    novelId,
                    json
            );

        });

    }

}
