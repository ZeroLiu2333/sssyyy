package com.ithem.lxcq.system.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ithem.lxcq.system.domain.user;
/*
用来循环生成多个user和token，在压力测试中，模仿多个用户访问
 */

public class UserUtils {
    public static void createUser(int count) throws Exception{
        List<user> users=new ArrayList<user>();
        for(int i=0;i<count;i++){
            user user1=new user();
            user1.setPassword("123"+i);
            user1.setUsername("user"+i);
            user1.setId(6+i);
            user1.setIp(i%2);
            users.add(user1);
        }
        System.out.println("craete users ----insert to db");
        //插入数据库
        Connection conn=DBUtils.getConn();
        String sql="insert into user (username,password,id,ip) values"
                + "(?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        for(int i=0;i<count;i++){
            user user=users.get(i);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setInt(3,user.getId());
            pstmt.setInt(4,user.getIp());
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt.close();
        conn.close();
        System.out.println("登录，生成token");
        //登录，使之生成一个token
        /*
        一定要启动springboot项目，不然就无法登陆并生成token
         */
        String urlString="http://localhost:80/login/do_login";
        File file=new File("D:/tokens.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf=new RandomAccessFile(file,"rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            user user=users.get(i);
            URL url=new URL(urlString);
            HttpURLConnection co=(HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out=co.getOutputStream();
            String params="username="+user.getUsername()+"&password="
                    +user.getPassword();
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream=co.getInputStream();
            ByteArrayOutputStream bout=new ByteArrayOutputStream();
            byte buff[]=new byte[1024];
            int len=0;
            while((len=inputStream.read(buff))>=0) {
                bout.write(buff,0,len);
            }
            inputStream.close();
            bout.close();
            String response=new String(bout.toByteArray());
            JSONObject jo=JSON.parseObject(response);
            String token=jo.getString("data");
            System.out.println("user:"+user.getId()+"	token:"+token);
            String row=user.getId()+","+token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : "+user.getId());
        }
    }
    public static void main(String[] args) throws Exception {
        createUser(5000);
    }
    }

