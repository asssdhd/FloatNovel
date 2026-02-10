package org.example.floatnovel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {

    private Long id;//

    private Long userId;//用户id

    private String authType;//登录方式：手机号验证码、用户名密码

    private String authKey;//登录标识：用户名

    private String credential;//密码hash：加密后的密码

    private Integer status;//凭证状态： 0启用，1禁止

    private LocalDate CreateTime;//创建时间

    private LocalDate UpdateTime;//更新时间

}
