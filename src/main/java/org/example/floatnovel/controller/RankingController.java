package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.floatnovel.entity.Result;

import org.example.floatnovel.service.SortedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rank")
@Tag(name = "排行模块")
public class RankingController {

    @Autowired
    private SortedService soretdService;

    /*
    * 热度榜
    * */
    @GetMapping("/hot")
    @Operation(summary = "获取排行接口")
    public Result<?> getHotRank(@RequestParam(defaultValue = "10") int topN) {
        return Result.success(soretdService.getHotRank(topN));
    }
}
