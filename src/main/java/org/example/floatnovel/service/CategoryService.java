package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.DTO.CategoryDTO;
import org.example.floatnovel.entity.Result;

import java.util.List;

public interface CategoryService extends IService<CategoryDTO> {

    Result<List<CategoryDTO>> getCategory();

    Result addCategoryToNovel(Long novelId, Long categoryId);

    Result deleteCategory(Long categoryId);

    Result updateCategory(CategoryDTO categoryDTO);
}
