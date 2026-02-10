package org.example.floatnovel.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationLogEntity {

    private Long id;

    private String module;
    private String operation;
    private String description;

    private String method;
    private String requestParams;

    private String operator;     // 当前用户
    private String ip;

    private LocalDateTime operateTime;
}
