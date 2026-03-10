package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;


public interface NovelService extends IService<Novel> {


    Result add(Novel novel);

    Page<Novel> NovelPage(int pageNum, int pageSize, String name, String author, Long categoryId);

    Result<Novel> getById(Long id);

    void collect(Long novelId);
}
