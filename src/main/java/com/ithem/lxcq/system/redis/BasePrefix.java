package com.ithem.lxcq.system.redis;

public  abstract class BasePrefix implements KeyPrefix{
    public int expireSeconds;
    public String prefix;
    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }
    public BasePrefix(String prefix){
        this.prefix=prefix;
        this.expireSeconds=0;
    }
    public int expireSeconds(){
        return expireSeconds;
    }
    public String getPrefix(){
        return prefix+":";
    }
}
