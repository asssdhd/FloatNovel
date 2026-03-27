package org.example.floatnovel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.floatnovel.VO.ReadProgressVO;
import org.example.floatnovel.entity.ReadProgress;
import org.example.floatnovel.entity.Result;

public interface ReadProgressService extends IService<ReadProgress> {
    Result saveProgress(ReadProgress readProgress);

    Result<ReadProgressVO> getProgress( Long novelId);
}
