package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;

import java.util.List;

public interface CommentService {
    Result add(Comment comment);

    Page<Comment> list(Integer pageNum, Integer pageSize);

    Result<List<Comment>> getByNovelId(Long novelId);
}
