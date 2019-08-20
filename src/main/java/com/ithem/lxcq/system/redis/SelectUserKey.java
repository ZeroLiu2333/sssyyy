package com.ithem.lxcq.system.redis;



public class SelectUserKey extends BasePrefix {
    //token的有效期
    public static final int TOKEN_EXPIRE=3600*24*2;//3600S*24*2    =2天
    public SelectUserKey(int expireSeconds,String prefix){
        super(expireSeconds,prefix);
    }
    //cookie的id保存在redis中
    public  static SelectUserKey token=new SelectUserKey(TOKEN_EXPIRE,"tk");
    //选课的用户信息保存在redis中，用户保存在redis一般都是永久有效的
    public  static SelectUserKey getById=new SelectUserKey(0,"id");
}
