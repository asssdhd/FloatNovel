package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mappper.NovelMapper;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NovelServiceimpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {


    @Autowired
    private NovelMapper novelMapper;



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
}
