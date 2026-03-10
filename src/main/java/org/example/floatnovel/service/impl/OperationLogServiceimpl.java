package org.example.floatnovel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.floatnovel.entity.OperationLog;
import org.example.floatnovel.mapper.OperationLogMapper;
import org.example.floatnovel.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceimpl extends ServiceImpl<OperationLogMapper, OperationLog>
        implements OperationLogService {

}
