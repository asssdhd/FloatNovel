package org.example.floatnovel.utility;

import org.apache.tika.sax.SecureContentHandler;
import org.example.floatnovel.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
* 获取用户信息类
* */
public class GetUser {



  public static Long  getUserId(){
  //获取Authentication
      Authentication authentication = SecurityContextHolder
              .getContext()
              .getAuthentication();

      //获取用户
      User user=(User) authentication.getPrincipal();

      //返回用户id
      return user.getId();

  }

  public static String getUserName(){
      //获取Authentication
      Authentication authentication = SecurityContextHolder
              .getContext()
              .getAuthentication();

      //获取用户
      User user=(User) authentication.getPrincipal();

      //返回用户名
      return user.getName();
  }



}
