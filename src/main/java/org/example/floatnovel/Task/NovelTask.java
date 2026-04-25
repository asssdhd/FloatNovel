package org.example.floatnovel.Task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NovelTask {

    @Scheduled(fixedRate=1000)//TODO 定时为一天一次，0点开始
    public void  NovelSimilarity(){
        //获取所有小说已经其标签


        //
        //根据小说标签建一个向量表

        //

    }
}
