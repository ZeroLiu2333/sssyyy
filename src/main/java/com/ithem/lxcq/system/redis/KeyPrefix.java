package com.ithem.lxcq.system.redis;

public interface KeyPrefix {
    //有效期
    public int expireSeconds();
    //前缀
    public String getPrefix();
}
