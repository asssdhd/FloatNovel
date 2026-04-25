package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.DTO.CatalogueDTO;
import org.example.floatnovel.DTO.ChapterDTO;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.entity.ChapterContent;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.mapper.ChapterContentMapper;
import org.example.floatnovel.mapper.ChapterMapper;
import org.example.floatnovel.mapper.NovelMapper;
import org.example.floatnovel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChapterServiceimpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private ChapterContentMapper chapterContentMapper;

    @Autowired
    private NovelMapper novelMapper;


    /*
    章节上传
    2025.12.29
     */
    @Transactional
    public Result upload(Long novelId, String title, String content, Integer orders) throws IOException {

        //上传章节
        Chapter chapter = new Chapter();

        chapter.setNovelId(novelId);
        chapter.setTitle(title);
        chapter.setOrders(orders);
        chapter.setCreateTime(LocalDateTime.now());

        chapterMapper.insert(chapter);



        //上传章节内容
        ChapterContent chapterContent = new ChapterContent();
        chapterContent.setChapter_id(chapter.getId());
        chapterContent.setContent(content);

        
        chapterContentMapper.insert(chapterContent);



        //返回结果
        return Result.success();
    }

    /*
        获取小说目录
        2025.12.30
         */
    public Result<List<CatalogueDTO>> Catalogue(Long novelId) {

        List<CatalogueDTO> catalogue=chapterMapper.Catalogue(novelId);//查询小说的所有章节

        return Result.success(catalogue);
    }

    /*
    * 阅读小说
    * */

    public Result<ChapterDTO> read(Long chapterId,Long novelId) {


        ChapterDTO chapterDTO=chapterMapper.read(chapterId);

       novelMapper.UpdateViewCountOne(novelId);

        return Result.success(chapterDTO);
    }
    /*
    * 删除章节
    * */
    @Transactional//一致性注解
    public Result deleteChapter(Long chapterId) {
        //删除章节表的记录
         chapterMapper.deleteById(chapterId);

        //删除章节内容表的记录
        chapterContentMapper.deleteByChapterId(chapterId);

        return Result.success();
    }
}
