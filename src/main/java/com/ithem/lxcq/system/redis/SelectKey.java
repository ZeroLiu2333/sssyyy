package com.ithem.lxcq.system.redis;

public class SelectKey extends BasePrefix {
    public  SelectKey(String prefix){
        super(prefix);
    }
    public static SelectKey sk=new SelectKey("sk");
}
