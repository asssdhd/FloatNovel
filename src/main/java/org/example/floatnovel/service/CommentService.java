package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.floatnovel.entity.Comment;
import org.example.floatnovel.entity.Result;

public interface CommentService {
    Result add(Comment comment);

    Page<Comment> list(Integer pageNum, Integer pageSize);
}
