package com.ithem.lxcq.system.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    //获取连接池，数据库数据的方法
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis=null;//获取数据的一个链接
        try{
            jedis=jedisPool.getResource();
            String k1=prefix.getPrefix()+key;
            String s=jedis.get(k1);
            /*
            get方法返回的数据类型是泛型，因此需要将数据装箱后输出
            数据库进行get操作后得到的是一个包装类型
             */
            T t=stringToBean(s,clazz);
            return t;
        }finally {
            jedis.close();
        }
    }

    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis=null;//获取数据的一个链接
        try{
            jedis=jedisPool.getResource();
            String v1=BeanToString(value);
            String k1=prefix.getPrefix()+key;
            jedis.set(k1,v1);
            return true;
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }
    /**
     * 移除对象,删除
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            long ret=jedis.del(realKey);
            return ret>0;//删除成功，返回大于0
            //return jedis.decr(realKey);
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }
    /**
     * 减少值
     * @param prefix
     * @param key
     * @return
     */
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            jedis.close();
        }
    }

    //获取连接池，数据库数据的方法
    public <T> T get(String key,Class<T> clazz){
        Jedis jedis=null;//获取数据的一个链接
        try{
            jedis=jedisPool.getResource();
            String s=jedis.get(key);
            /*
            get方法返回的数据类型是泛型，因此需要将数据装箱后输出
            数据库进行get操作后得到的是一个包装类型
             */
            T t=stringToBean(s,clazz);
            return t;
        }finally {
            jedis.close();
        }
    }
   public <T> boolean set(String key,T value){
        Jedis jedis=null;//获取数据的一个链接
        try{
            jedis=jedisPool.getResource();
            String v1=BeanToString(value);
            jedis.set(key,v1);
            return true;
        }finally {
            if(jedis!=null) {
                jedis.close();
            }
        }
    }

    public <T> T stringToBean(String s,Class<T> clazz){
        if(s==null||s.length()==0||clazz==null) return null;
        if(clazz==int.class||clazz==Integer.class){
            return (T)Integer.valueOf(s);
        }else if(clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(s);
        }else if(clazz==String.class){
            return (T)s;
        }else{
            return (T)JSON.toJavaObject(JSON.parseObject(s),clazz);
        }
    }
    public <T> String BeanToString(T value) {
        if(value==null)return null;
        if(value.getClass()==int.class||value.getClass()==Integer.class){
            return ""+value;
        }else if(value.getClass()==long.class||value.getClass()==Long.class){
            return ""+value;
        }else if(value.getClass()==String.class){
            return (String)value;
        }else{
            return JSON.toJSONString(value);
        }

    }

}
