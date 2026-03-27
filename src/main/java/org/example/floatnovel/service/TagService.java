package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.entity.Tag;

public interface TagService extends IService<Tag> {

    Result addTag(Tag tag);
}
