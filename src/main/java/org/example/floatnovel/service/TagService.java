package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {

    Result addTag(Tag tag);

    Result<List<Tag>> getAll();

    Result deleteTag(Long tagId);
}
