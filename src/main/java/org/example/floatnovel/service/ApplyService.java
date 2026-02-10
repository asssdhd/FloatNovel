package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.entity.Apply;
import org.example.floatnovel.entity.Result;

public interface ApplyService extends IService<Apply> {

    Result apply(Apply apply);

    Result audit(Apply apply);

    Page<Apply> applyPage(Integer pageNum, Integer pageSize);
}
