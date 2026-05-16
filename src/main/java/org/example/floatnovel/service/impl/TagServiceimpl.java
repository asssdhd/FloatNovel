package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.entity.NovelTag;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.Tag;
import org.example.floatnovel.mapper.NovelTagMapper;
import org.example.floatnovel.mapper.TagMapper;
import org.example.floatnovel.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class TagServiceimpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private NovelTagMapper novelTagMapper;
    /*
    * 添加标签
    * */
    public Result addTag(Tag tag) {

        tag.setCreateTime(LocalDateTime.now());
        tagMapper.insert(tag);

        return Result.success();
    }

    @Override
    public Result<List<Tag>> getAll() {

        List<Tag> tags=tagMapper.getAll();



        return Result.success(tags);
    }
    /*
    * 删除标签
    * */
    @Override
    public Result deleteTag(Long tagId) {


            tagMapper.deleteById(tagId);


        return Result.success();
    }

    @Override
    public Result addTagToNovel(Long novelId, Long tagId) {

        NovelTag novelTag = new NovelTag();
        novelTag.setNovelId(novelId);
        novelTag.setTagId(tagId);
        //
        novelTagMapper.insert(novelTag);

        return Result.success();
    }

    @Override
    public Result deleteTagToNovel(Long novelId, Long tagId) {

        novelTagMapper.deleteByNovelIdAndTagId(novelId,tagId);

        return Result.success();
    }
}
