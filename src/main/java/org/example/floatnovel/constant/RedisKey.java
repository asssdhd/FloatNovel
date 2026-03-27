package org.example.floatnovel.constant;

public class RedisKey {

    public static final String READPROGRESS="read:progress:";//记录用户的阅读记录key,这个需要拼接
    public static final String READPENDING="read:pending";//记录更改的阅读记录key,这个不需要拼接
    public static final String READRECENT="read:recent:";//记录用户的最近浏览key,这个需要拼接

}
