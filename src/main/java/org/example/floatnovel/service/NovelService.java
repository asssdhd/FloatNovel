package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.DTO.NovelDTO;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;

import java.util.List;


public interface NovelService extends IService<Novel> {


    Result add(Novel novel);

    Page<Novel> NovelPage(int pageNum, int pageSize, String name, String author, Long categoryId);

    Result<Novel> getById(Long id);

    Result collect(Long novelId);

    Result set(NovelDTO novelDTO);

    Result<List<Novel>> getByAuthorName(String author);

    List<Novel> getAll();
}
