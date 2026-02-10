package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.common.LoginUser;
import org.example.floatnovel.entity.Bookshelf;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.BookshelfMapper;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class NovelServiceimpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {


    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private BookshelfMapper bookshelfMapper;



    @Override
    public Result add(Novel novel) {

        save(novel);

        return  Result.success();
    }

    @Override
    public Page<Novel> NovelPage(int pageNum, int pageSize) {
        Page<Novel> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Novel> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Novel::getCreateTime);
        return this.page(page, wrapper);
    }


     /* 第二阶段
    获取小说详情
            2025.10.15*/
    @Override
    public Result<Novel> getById(Long id) {

        Novel novel = novelMapper.selectById(id);

        return  Result.success(novel);

    }

    /*
    收藏小说
    2025.12.7
     */
    @Override
    public void collect(Long novelId) {
        //1.获取用户的ID
         //1.1从security中获取loginUser对象
         LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext()
               .getAuthentication()
               .getPrincipal();

         //1.2取出用户ID
        Long userId = loginUser.getId();

        //查询小说名和封面
        Novel novel = novelMapper.selectById(novelId);


        //创建书架对象
        Bookshelf bookshelf = new Bookshelf();

        bookshelf.setUserId(userId);//设置用户
        bookshelf.setNovelId(novelId);//设置小说
        bookshelf.setNovelName(novel.getName());//设置书名
        bookshelf.setCover(novel.getCover());//设置书架小说封面
        bookshelf.setCreateTime(LocalDateTime.now());//设置创建时间
        bookshelf.setUpdateTime(LocalDateTime.now());//设置修改时间
        bookshelf.setIsDeleted(false);//将状态设置为正常，true未已删除

        bookshelfMapper.insert(bookshelf);


    }
}
