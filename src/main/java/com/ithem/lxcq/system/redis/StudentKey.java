package com.ithem.lxcq.system.redis;

public class StudentKey extends BasePrefix {
    public StudentKey(int expireSeconds,String prefix){
        super(expireSeconds,prefix);
    }
    public StudentKey(String prefix){
        super(prefix);
    }
    public static StudentKey student1=new StudentKey("s1");
}
