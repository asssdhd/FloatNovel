package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/chapter")
@Tag(name = "章节模块")
public class ChapterController {

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private FileStorageService fileStorageService;//注入实列



/*第二阶段
    上传章节
    需要作者权限
        2025.10.10*/
    //TODO 权限管理
 @PostMapping("/upload")
 @Operation(summary = "上传章节接口")
 public Result  UplaodChapter(

         @RequestParam("novelId") Long novelId,
         @RequestParam("title") String title,
         @RequestParam("chapter") MultipartFile file,
         @RequestParam("orders") Integer orders

         ) throws IOException {

     return chapterService.upload(novelId,title,file,orders);
 }





}
