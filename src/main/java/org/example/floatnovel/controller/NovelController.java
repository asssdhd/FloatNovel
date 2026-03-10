package org.example.floatnovel.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.DTO.CatalogueDTO;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Apply;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.ApplyService;
import org.example.floatnovel.service.ChapterService;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/novel")
@Tag(name = "小说模块")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ChapterService chapterService;


/*
* 添加小说
* 需要作者权限
* */
    @PostMapping("/add")
    @Operation(summary = "添加小说接口")
    public Result addNovel(@RequestBody Novel novel){
         log.info("进入controller");


        return novelService.add(novel);

    }

/*
* 查询小说例表
* */
    @GetMapping("/list")
    @Operation(summary = "小说列表接口")
    public Result<Page<Novel>> NovelPage(@RequestParam int pageNum,
                                         @RequestParam int pageSize,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String author,
                                         @RequestParam(required = false) Long categoryId

    ){

        Page<Novel> page = novelService.NovelPage(pageNum, pageSize, name, author, categoryId);

        return Result.success(page);

    }

   /* 第二阶段
    获取小说详情
            2025.10.15*/
    @GetMapping("/{id}")
    @Operation(summary = "查询小说详情接口")
    public Result<Novel> get(@PathVariable Long id){

        return novelService.getById(id);
    }


    /*
    收藏小说
    2025.12.7
     */
    @PostMapping("/collect")
    @Operation(summary = "收藏小说接口")
    public Result collect(@RequestParam Long novelId){

         novelService.collect(novelId);

        return Result.success();
    }


    /*
    删除小说
    2025.12.12
    需要作者权限
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除小说接口")
    public Result delete(Long id){


        novelService.removeById(id);

        return Result.success();

    }


    /*
    阅读小说
    2025.12.30
     */
    @GetMapping("/read")
    @Operation(summary = "阅读小说接口")
    public Result<ChapterDTO> read(@RequestParam Long chapterId){

        return chapterService.read(chapterId);
    }



    /*
    获取小说目录
    2025.12.30
     */
    @GetMapping("/Catalogue")
    @Operation(summary = "查询小说目录")
    public Result<List<CatalogueDTO>>  getCatalogue(@RequestParam Long novelId){
        return chapterService.Catalogue(novelId);
    }









}
