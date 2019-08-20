package com.ithem.lxcq.system.redis;

public class CourseKey extends BasePrefix{
    public CourseKey(int expireSeconds,String prefix){
        super(expireSeconds,prefix);
    }
    public static CourseKey sck=new CourseKey(0,"sck");
    public static CourseKey tck=new CourseKey(60,"tck");


}
