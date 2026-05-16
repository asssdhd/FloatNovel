package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.DTO.CategoryDTO;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.CategoryMapper;
import org.example.floatnovel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceimpl extends ServiceImpl<CategoryMapper, CategoryDTO> implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    /*
     * 获取小说分类
     * 2026-4-11*/
    public Result<List<CategoryDTO>> getCategory() {


        List<CategoryDTO> list=new ArrayList<>();

        list=categoryMapper.getCategoryList();

        return Result.success(list);
    }

    @Override
    public Result addCategoryToNovel(Long novelId, Long categoryId) {

        categoryMapper.addCategoryToNovel(novelId,categoryId);

        return Result.success();
    }

    @Override
    public Result deleteCategory(Long categoryId) {

        categoryMapper.deleteById(categoryId);

        return Result.success();
    }

    @Override
    public Result updateCategory(CategoryDTO categoryDTO) {

        categoryMapper.updateById(categoryDTO);

        return Result.success();
    }
}
