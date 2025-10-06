package org.example.floatnovel.utility;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeyGenerator {

    public static void main(String[] args) {
        // 1. 生成一个随机的 SecretKey（默认强度 >= 256 bit）
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

        // 2. 转成 Base64 字符串，方便存到配置文件/配置中心
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        System.out.println("随机生成的 JWT SecretKey:");
        System.out.println(base64Key);
        System.out.println("长度: " + base64Key.length() + " 字符");
    }
}
