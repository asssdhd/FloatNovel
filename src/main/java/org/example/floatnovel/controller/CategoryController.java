package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.DTO.CategoryDTO;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
     * 获取小说分类
     * 2026-4-11*/
    @GetMapping("/category")
    @Operation(summary = "获取小说分类")
    public Result<List<CategoryDTO>> getCategory(){

        return categoryService.getCategory();

    }

    /* TODO
    * 添加分类
    * */
    @PostMapping
    @Operation(summary = "添加分类")
    public Result addCategoryToNovel(@RequestParam Long novelId,@RequestParam Long categoryId){

        return categoryService.addCategoryToNovel(novelId,categoryId);

    }

    /*TODO
    删除分类 逻辑删除
    * */
    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result deleteCategory(@RequestParam Long categoryId){

        return categoryService.deleteCategory(categoryId);

    }


    /*TODO
     * 修改分类
    * */
    @PutMapping
    @Operation(summary = "修改分类")
    public Result updateCategoryToNovel(@RequestBody CategoryDTO categoryDTO){

        return categoryService.updateCategory(categoryDTO);

    }

}
