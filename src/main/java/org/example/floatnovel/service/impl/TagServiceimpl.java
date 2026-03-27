package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.Tag;
import org.example.floatnovel.mapper.TagMapper;
import org.example.floatnovel.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TagServiceimpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;
    /*
    * 添加标签
    * */
    public Result addTag(Tag tag) {

        tag.setCreateTime(LocalDateTime.now());
        tagMapper.insert(tag);

        return Result.success();
    }
}
