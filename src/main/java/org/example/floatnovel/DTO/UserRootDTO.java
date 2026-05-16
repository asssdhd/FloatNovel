package org.example.floatnovel.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRootDTO {
    @TableId(type = IdType.ASSIGN_ID)//MyBatis-Plus自动生成全局唯一的 Long 类型 ID
    private Long id;//用户id

    private String nickname;//昵称

    private String password;//密码

    private String  username;//用户名

    private String avatar;//头像

    private Integer gender;//性别：0未知，1男，2女

    private Integer status;//状态： 0启用，1封号

    private LocalDate CreatedTime;//创建时间

    private LocalDate UpdatedTime;//更新时间
}
