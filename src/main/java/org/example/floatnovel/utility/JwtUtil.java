package org.example.floatnovel.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
     private String secret;

    @Value("${jwt.expiration}")
    private  Long expiration;

    private  SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //生成token令牌
    public  String generateToken(String username, Collection<String> roles) {

        //获取现在的时间
        long now = System.currentTimeMillis();
       return Jwts.builder()
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)//需要指定签名算法和密钥
                .setSubject(username)//需要指定用户名
                .claim("roles",roles)//需要写入用户角色
                .setIssuedAt(new Date(now))  //设置签发时间
                .setExpiration(new Date(now+expiration))//设置过期时间
                .compact();
    }



    //解析jwt
    public Claims parseToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // 必须用 SecretKey
                .build()//创建jwtParser解析器对象
                .parseClaimsJws(token)
                .getBody();//取出 Payload 部分

    }


    //判断JWT令牌是否为真
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //获取用户名
    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Object roles = parseToken(token).get("roles");
        if (roles instanceof List) {
            return ((List<?>) roles).stream().map(Object::toString).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
