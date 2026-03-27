package org.example.floatnovel.Task;

import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.constant.RedisKey;
import org.example.floatnovel.entity.ReadProgress;
import org.example.floatnovel.mapper.ReadProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ProgressTask {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;//操作redis的stringRedisTemplate

    @Autowired
    private ReadProgressMapper readProgressMapper;

    @Scheduled(fixedRate = 1000 * 5)
    public void ProgressTask(){


        //从Redis中获取set集合的内容
        Set<String> pendings = stringRedisTemplate.opsForSet().members(RedisKey.READPENDING);

        //遍历set集合
        for (String pending : pendings) {

            //根据pending从redis hash中取出阅读记录
            String HashKey=RedisKey.READPROGRESS+pending;

            Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(HashKey);
            log.info("map里的值:{}",map);

            //把userId、novelId从pendings取出来
            String[] split = pending.split(":");

            String userId= split[0];//取出String类型的userId
            String  novelId= split[1];//取出String类型的novelId

            //从hash中获取章节ID、偏移量、更新时间
            Object chapterIdObj = map.get("chapterId");
            Object offsetObj = map.get("offset");
            Object OupdateTime = map.get("updateTime");

            //将更新时间转换为LocalDateTime类型
            LocalDateTime updateTime = Instant.ofEpochMilli(Long.valueOf(OupdateTime.toString()))
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            //创建进度实体类
            ReadProgress readProgress = new ReadProgress();




            readProgress.setUserId(Long.valueOf(userId));//设置用户ID
            readProgress.setNovelId(Long.valueOf(novelId));//设置小说ID
            readProgress.setChapterId(Long.valueOf(chapterIdObj.toString()));//
            readProgress.setOffset(Long.valueOf(offsetObj.toString()));//
            readProgress.setUpdateTime(updateTime);//设置更新时间

            log.info("定时任务保存的readProgress对象:{}",readProgress);
            //写入数据库
            readProgressMapper.saveReadProgress(readProgress);


            //删除掉redis set中的记录
            stringRedisTemplate.opsForSet().remove(RedisKey.READPENDING,pending);

        }


    }
}
