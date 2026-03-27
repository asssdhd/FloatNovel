package org.example.floatnovel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.DTO.NovelDTO;
import org.example.floatnovel.common.LoginUser;
import org.example.floatnovel.entity.*;
import org.example.floatnovel.mapper.BookshelfMapper;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.mapper.NovelTagMapper;
import org.example.floatnovel.mapper.TagMapper;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Service
public class NovelServiceimpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {


    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private BookshelfMapper bookshelfMapper;

    @Autowired
    private NovelTagMapper novelTagMapper;





    @Override
    public Result add(Novel novel) {

        save(novel);

        return  Result.success();
    }

    @Override
    public Page<Novel> NovelPage(int pageNum, int pageSize, String name, String author, Long categoryId) {

        Page<Novel> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Novel> wrapper = new LambdaQueryWrapper<>();

        // 动态拼接条件
        if (StringUtils.hasText(name)) {
            wrapper.like(Novel::getName, name);
        }

        if (StringUtils.hasText(author)) {
            wrapper.like(Novel::getAuthor, author);
        }

        if (categoryId != null) {
            wrapper.eq(Novel::getCategory, categoryId);
        }

        wrapper.orderByDesc(Novel::getId);

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
    添加小说进书架
    2025.12.7
     */
    @Override
    public void collect(Long novelId) {
        //1.获取用户的ID
         //1.1从security中获取loginUser对象
        Long userId = StpUtil.getLoginIdAsLong();




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

    /*
     * 修改小说
     * */
    public Result set(NovelDTO novelDTO) {

        //将小说修改更新到数据库
        Novel novel = new Novel();
        BeanUtils.copyProperties(novelDTO, novel);

        //仅当小说的内容修改时才更新小说
        if (novelDTO.getName() != null ||
                novelDTO.getAuthor() != null ||
                novelDTO.getIntro() != null ||
                novelDTO.getCover() != null ||
                novelDTO.getCategory() != null) {
            novelMapper.updateById(novel);
        }
        //将小说标签的修改增加到数据库

        // 2 删除旧标签
        novelTagMapper.deleteByNovelId(novelDTO.getId());

        // 3 插入新标签
        if (novelDTO.getTagIds() != null && !novelDTO.getTagIds().isEmpty()) {

            for (Long tagId : novelDTO.getTagIds()) {

                NovelTag novelTag = new NovelTag();
                novelTag.setNovelId(novelDTO.getId());
                novelTag.setTagId(tagId);

                novelTagMapper.insert(novelTag);
            }
        }


        return Result.success();
    }
}
