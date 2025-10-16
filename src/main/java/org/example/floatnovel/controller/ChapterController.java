package org.example.floatnovel.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.ChapterService;
import org.example.floatnovel.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    private QiniuService qiniuService;

    public  ChapterController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }
/*第二阶段
    上传章节
        2025.10.10*/
 @PostMapping("/upload")
 public Result  UplaodChapter(

         @RequestParam("novelId") Long novelId,
         @RequestParam("title") String title,
         @RequestParam("chapter") MultipartFile file,
         @RequestParam("orders") Integer orders

         ) {
     String url="";

     //上传章节内容
     try {
         url = qiniuService.uploadFile(file.getInputStream(), file.getOriginalFilename());
         log.info("上传章节成功");
     } catch (Exception e) {
         /*throw new RuntimeException(e);*/
         log.info("上传章节失败");
     }

     //封装Chapter对象
     Chapter chapter = new Chapter();

     chapter.setNovelId(novelId);
     chapter.setTitle(title);
     chapter.setContent(url);
     chapter.setCreateTime(LocalDateTime.now());
     chapter.setOrders(orders);

     //保存到数据库
     chapterService.upload(chapter);


     return Result.success();
 }


}
