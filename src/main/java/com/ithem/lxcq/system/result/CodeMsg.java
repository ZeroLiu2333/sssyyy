package com.ithem.lxcq.system.result;

public class CodeMsg {
    private int code;
    private String msg;
    public static CodeMsg success=new CodeMsg(0,"sucess");
    public static CodeMsg userError=new CodeMsg(1,"用户名或密码不正确");
    public static CodeMsg userEmpet=new CodeMsg(2,"用户不存在");
    public static CodeMsg prepare=new CodeMsg(3,"选课没开始");
    public static CodeMsg END=new CodeMsg(4,"选课结束");
    public static CodeMsg NotFull=new CodeMsg(5,"人数已满，选课失败");
    public static CodeMsg Repeate=new CodeMsg(6,"不能重复选课");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
    private CodeMsg(int code,String msg){
        this.code=code;
        this.msg=msg;

    }
}
