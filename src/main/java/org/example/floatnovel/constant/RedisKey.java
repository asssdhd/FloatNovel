package org.example.floatnovel.constant;

public class RedisKey {

    public static final String READPROGRESS="read:progress:";//记录用户的阅读记录key,这个需要拼接
    public static final String READPENDING="read:pending";//记录更改的阅读记录key,这个不需要拼接
    public static final String READRECENT="read:recent:";//记录用户的最近浏览key,这个需要拼接

    public static final String VIEW_RANK_KEY = "rank:novel:view";//热门榜Key
    public static final String VIEW_INFO_KEY = "rank:info:view";//热门榜详细信息Key

    public static final String COLLECT_RANK_KEY = "rank:novel:collect";//收藏榜Key
    public static final String COLLECT_INFO_KEY = "rank:info:collect";//热门榜详细信息Key

    public static final String SCORE_RANK_KEY = "rank:novel:score";//评分榜Key
    public static final String SCORE_INFO_KEY = "rank:info:score";//热门榜详细信息Key

}
