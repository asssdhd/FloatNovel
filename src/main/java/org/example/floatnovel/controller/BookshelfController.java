package org.example.floatnovel.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.floatnovel.DTO.DeleteBookDTO;
import org.example.floatnovel.VO.BookshelfVO;
import org.example.floatnovel.entity.Bookshelf;
import org.example.floatnovel.entity.Result;
import org.example.floatnovel.service.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/bookshelf")
@Tag(name = "书架模块")
public class BookshelfController {


    @Autowired
    private BookshelfService bookshelfService;

    /*
    删除书架上的小说
    2025.12.12
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除小说")
    public Result delete(@RequestBody DeleteBookDTO deleteBookDTO){

        return bookshelfService.delete(deleteBookDTO.getIds());
    }

    /*
    查看书架
    2025.12.13
     */
    @GetMapping
    @Operation(summary = "查看书架")
    public Result<List<BookshelfVO>> getAll(){


       List<BookshelfVO>  list= bookshelfService.getAll();

       log.info("书架返回的数据:{}",list);
        return Result.success(list);
    }



}
