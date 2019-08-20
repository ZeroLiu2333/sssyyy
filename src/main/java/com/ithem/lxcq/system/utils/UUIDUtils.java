package com.ithem.lxcq.system.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String uid(){
        return UUID.randomUUID().toString().replace("-","");//去掉原生的"-"
    }
}
