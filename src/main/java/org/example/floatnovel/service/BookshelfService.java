package org.example.floatnovel.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.VO.BookshelfVO;
import org.example.floatnovel.entity.Bookshelf;
import org.example.floatnovel.entity.Result;

import java.util.List;

public interface BookshelfService extends IService<Bookshelf> {


    Result delete(List<Long> ids);

    List<BookshelfVO> getAll();
}
