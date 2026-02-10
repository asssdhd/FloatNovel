package org.example.floatnovel.service;

import org.example.floatnovel.entity.Rating;
import org.example.floatnovel.mapper.RatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SortedService {

    @Autowired
    private RatingMapper ratingMapper;
    //依赖注入RedisStringTeplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static final String HOT_RANK_KEY = "rank:hot";

    //设置定时任务更新排行榜
    @Scheduled(cron = "0 0 * * * ?") // 每小时执行一次
    public void updateHotRank(){

        // 查询所有评分记录
        List<Rating> ratings = ratingMapper.selectList(null);

        // 计算小说的平均分和人数
        Map<Long, List<Integer>> novelScores = new HashMap<>();
        for (Rating r : ratings) {
            novelScores.computeIfAbsent(r.getNovelId(), k -> new ArrayList<>()).add(r.getScore());
        }

        // 清空旧的排行榜
        stringRedisTemplate.delete(HOT_RANK_KEY);

        // 计算热度值 = 平均分 × 评分人数，并写入 Redis
        for (Map.Entry<Long, List<Integer>> entry : novelScores.entrySet()) {
            Long novelId = entry.getKey();
            List<Integer> scores = entry.getValue();

            double avg = scores.stream().mapToInt(Integer::intValue).average().orElse(0);
            double heat = avg * scores.size();

            stringRedisTemplate.opsForZSet().add(HOT_RANK_KEY, novelId.toString(), heat);
        }



    }

    /**
     * 获取热门榜单前N名
     */
    public List<Map<String, Object>> getHotRank(int topN) {
        Set<ZSetOperations.TypedTuple<String>> result =
                stringRedisTemplate.opsForZSet().reverseRangeWithScores(HOT_RANK_KEY, 0, topN - 1);

        List<Map<String, Object>> list = new ArrayList<>();
        if (result != null) {
            for (ZSetOperations.TypedTuple<String> item : result) {
                Map<String, Object> map = new HashMap<>();
                map.put("novelId", item.getValue());
                map.put("score", item.getScore());
                list.add(map);
            }
        }
        return list;
    }




}
