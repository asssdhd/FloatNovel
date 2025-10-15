package org.example.floatnovel.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.floatnovel.entity.Novel;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @PostMapping("/add")
    public Result addNovel(@RequestBody Novel novel){

        return novelService.add(novel);

    }

    @GetMapping("/page")
    public Result<Page<Novel>> NovelPage(@RequestParam int pageNum,
                                  @RequestParam int pageSize){

        Page<Novel> page = novelService.NovelPage(pageNum, pageSize);

        return Result.success(page);

    }

   /* 第二阶段
    获取小说详情
            2025.10.15*/
    @GetMapping("/{id}")
    public Result<Novel> get(@PathVariable Long id){

        return novelService.getById(id);
    }

}
