package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
 @PostMapping()
 @Operation(summary = "上传章节接口")
 public Result  UplaodChapter(

         @RequestParam("novelId") Long novelId,
         @RequestParam("title") String title,
         @RequestParam("chapter") String content,
         @RequestParam("orders") Integer orders

         ) throws IOException {

     return chapterService.upload(novelId,title,content,orders);
 }

 /*
 * 删除章节
 * 2026.4.24
 * */
 @DeleteMapping
 @Operation(summary = "删除章节")
 public Result deleteChapter(Long chapterId){

     return chapterService.deleteChapter(chapterId);

 }

 /* TODO
 修改章节
 * */
    @PutMapping
    @Operation(summary = "修改章节")
    public Result updateChapter(@RequestBody ChapterDTO chapterDTO){
        return chapterService.updateChapter(chapterDTO);
    }


   /*
   * 获取章节详情接口
   * */
    @GetMapping
    @Operation(summary = "获取章节详情")
    public Result<ChapterDTO> getChapter(@RequestParam Long novelId, @RequestParam Long chapterId){
        return chapterService.getChapterInfo(novelId,chapterId);
    }


}
