package org.example.floatnovel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.floatnovel.common.ApplyStatus;

import java.time.LocalDateTime;

@TableName("author_apply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apply {

    private Long id;
    private Long userId;//申请用户ID
    private String authorName;//作者名
    private String contactInfo;//联系方式
    private String applyReason;//申请理由
    private Integer status;//申请状态
    private String auditReason;//审核意见
    private LocalDateTime auditTime;//审核时间
    private LocalDateTime createTime;//创建时间
    private  LocalDateTime updateTime;//更新时间

}
