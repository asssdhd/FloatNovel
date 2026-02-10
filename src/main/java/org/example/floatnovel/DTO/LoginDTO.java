package org.example.floatnovel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    /** 手机号 / 用户名 / 邮箱 */
    private String account;

    /** 登录方式：PASSWORD / CODE */
    private String loginType;

    /** 密码（密码登录用） */
    private String password;

    /** 验证码（验证码登录用） */
    private String code;
}
