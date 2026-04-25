package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import org.example.floatnovel.VO.BookshelfVO;
import org.example.floatnovel.common.LoginUser;
import org.example.floatnovel.common.constant.RedisConstant;
import org.example.floatnovel.entity.Bookshelf;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.BookshelfMapper;
import org.example.floatnovel.service.BookshelfService;
import org.example.floatnovel.utility.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookshelfServiceimpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements BookshelfService {

    @Autowired
    private BookshelfMapper bookshelfMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
    删除书架上的小说
    2025.12.12
     */
    @Override
    public Result delete(List<Long> ids) {

        //1.清理redis中的缓存防止脏读
          //1.1获取用户ID
        Long userId = StpUtil.getLoginIdAsLong();
          //1.2设置key
         String key= RedisConstant.BOOKSHELF_KEY+userId;

       //先删除数据库中的数据
       bookshelfMapper.deleteBYids(ids);

        //后删除缓存
        stringRedisTemplate.delete(key);

       return Result.success();

    }
    /*
       查看书架
       2025.12.13
        */
    @Override
    public List<BookshelfVO> getAll() {

       //获取用户id
        Long userId = StpUtil.getLoginIdAsLong();
        //查询Redis中缓存的信息，若不存在则查询写入redis
          //设置key
        String key= RedisConstant.BOOKSHELF_KEY+userId;

        String json = stringRedisTemplate.opsForValue().get(key);

        if(json !=null && !json.isEmpty()){

            //将json反序列化
            List<BookshelfVO> bookshelfVOS = JsonUtil.fromJson(json, new TypeReference<List<BookshelfVO>>() {
            });

            //返回数据
            return bookshelfVOS;
        }

        //查询到书架数据
        List<BookshelfVO> list= bookshelfMapper.getAll(userId);



        //如果数据库存在记录
          if(list!=null && !list.isEmpty()) {
              //将查询到的数据进行序列化
              String JsonList = JsonUtil.toJson(list);
              //存入Redis
              stringRedisTemplate.opsForValue().set(key, JsonList);
          }

        return list;
    }




}
