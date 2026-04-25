package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.Tag;
import org.example.floatnovel.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /*
     * 添加标签
     * */
    @PostMapping()
    @Operation(summary = "添加标签")
    public Result addTag(@RequestBody org.example.floatnovel.entity.Tag tag){

        return tagService.addTag(tag);

    }

    /*
     * 删除标签
     * */
    @DeleteMapping()
    @Operation(summary = "删除标签")
    public Result deleteTag(@RequestParam Long TagId){
        log.info("标签ID：{}",TagId);
        return tagService.deleteTag(TagId);

    }

    /*
     * 获取标签列表
     * */
    @GetMapping()
    @Operation(summary = "查看标签列表")
    private Result<List<Tag>> getTags(){
        return tagService.getAll();
    }
}
