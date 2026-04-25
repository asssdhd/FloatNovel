package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.VO.ReadProgressVO;
import org.example.floatnovel.constant.RedisKey;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.ReadProgress;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.mapper.ReadProgressMapper;
import org.example.floatnovel.service.ReadProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReadProgressServiceimpl extends ServiceImpl<ReadProgressMapper, ReadProgress>
        implements ReadProgressService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ReadProgressMapper readProgressMapper;


    @Autowired
    private NovelMapper novelMapper;

    /*
     * 保存阅读记录
     * 2026-3-20*
     */
    public Result saveProgress(ReadProgress readProgress) {

        //获取用户ID
        long userId = StpUtil.getLoginIdAsLong();

        //1.保存在redis Hash

          //1.1拼接保存用户阅读记录的redis key
          String HashKey=RedisKey.READPROGRESS+userId+
                ":"+readProgress.getNovelId();
          //1.2设置更新时间,依赖前端传可能为null
          long now = System.currentTimeMillis();
          //1.3创建要保存的hashMap集合
          HashMap<String, Object> ReadProgressValue = new HashMap<>();
          ReadProgressValue.put("chapterId",String.valueOf(readProgress.getChapterId()));//章节id转为String存储
          ReadProgressValue.put("offset",String.valueOf(readProgress.getOffset()));//偏移量转为String存储
          ReadProgressValue.put("updateTime",String.valueOf(now));//更新时间转为String存储

          stringRedisTemplate.opsForHash().putAll(HashKey,ReadProgressValue);//保存hash
          stringRedisTemplate.expire(HashKey,7, TimeUnit.DAYS);//设置过期时间为一周

        //2.保存Redis Zset
        String SetValue=userId+":"+readProgress.getNovelId();

        stringRedisTemplate.opsForSet().add(RedisKey.READPENDING,SetValue); //保存用于遍历写入mysql的阅读记录


        //把小说id和时间戳存储Zset记录用户阅读过的小说（扩展）
        stringRedisTemplate.opsForZSet().add(RedisKey.READRECENT+userId,
                String.valueOf(readProgress.getNovelId()), now);

        stringRedisTemplate.expire(RedisKey.READRECENT+userId,7, TimeUnit.DAYS);//设置过期时间为一周


        //只保存20条记录，在记录超过20条时才会真的删除
        stringRedisTemplate.opsForZSet()
                .removeRange(RedisKey.READRECENT + userId, 0, -21);


        return Result.success();
    }

    /*
     * 获取阅读记录
     * 2026-3-20
     * */
    public Result<ReadProgressVO> getProgress(Long novelId) {

        //创建返回对象
        ReadProgressVO readProgressVO = new ReadProgressVO();

        //获取用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        //查询redis
        String HashKey=RedisKey.READPROGRESS+userId+":"+novelId;

        log.info("redis存储的hashKey={}", HashKey);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(HashKey);

        System.out.println(map);

        readProgressVO.setNovelId(novelId);
       /* readProgressVO.setChapterId((Long) map.get("chapterId"));
        readProgressVO.setOffset((Long) map.get("offset"));*/
        Object chapterIdObj = map.get("chapterId");
        Object offsetObj = map.get("offset");

        if(chapterIdObj != null){
            readProgressVO.setChapterId(Long.valueOf(chapterIdObj.toString()));
        }

        if(offsetObj != null){
            readProgressVO.setOffset(Long.valueOf(offsetObj.toString()));
        }


        //redis查询不到,查询数据库
        if(map.isEmpty()){

            readProgressVO=readProgressMapper.selectByUserIdAndNovelId(userId,novelId);

        }

        log.info("readProgressVO:{}",readProgressVO);
        log.info("userId:{}",userId);


        return Result.success(readProgressVO);
    }

    /*
    * 获取用户浏览记录
    * */
    @Override
    public Result<List<Novel>> getNovelByCategoryId() {
        //1.从redis中获取阅读记录
          //1.1获取用户ID
          long userId = StpUtil.getLoginIdAsLong();
          //1.2获取全部记录
          Set<String> range = stringRedisTemplate.opsForZSet().range(RedisKey.READRECENT + userId, 0, -1);

          //把数据转为Long类型
          List<Long> novelIds = new ArrayList<>();
          for (String s : range) {
            novelIds.add(Long.valueOf(s));
          }

        //2.根据阅读记录从数据库中获取完整数据
          //2.1创建List
          List<Novel> novels=new ArrayList<>();

          //遍历获取数据
          for (Long novelId : novelIds) {
              Novel novel = novelMapper.selectById(novelId);
              novels.add(novel);
          }

        return Result.success(novels);
    }
}
