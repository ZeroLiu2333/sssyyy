package com.ithem.lxcq.system.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    //固定的salt，和用户密码进行拼装
    public final static String  salt="abc12345";
    public static String inputPassToFormPass(String inputPass) {
        String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        System.out.println(md5(str));
        return md5(str); 			//char类型计算会自动转换为int类型
    }
    //二次MD5
    public static String formPassToDBPass(String formPass,String salt) {//salt
        String str=""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    //数据库md5,使用数据库随机salt
    public static String inputPassToDbPass(String input,String saltDB) {
        String formPass=inputPassToFormPass(input);
        System.out.println(formPass);
        String dbPass=formPassToDBPass(formPass,saltDB);
        return dbPass;
    }





}
