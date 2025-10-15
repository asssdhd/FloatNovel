package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.Chapter;
import org.example.floatnovel.mappper.ChapterMapper;
import org.example.floatnovel.service.ChapterService;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceimpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {


    @Override
    public boolean upload(Chapter chapter) {
        save(chapter);

        return true;
    }
}
