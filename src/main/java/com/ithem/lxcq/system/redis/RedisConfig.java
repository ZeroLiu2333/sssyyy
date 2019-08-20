package com.ithem.lxcq.system.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="redis")//将application.properties里面前缀redis都读取
public class RedisConfig {
    private String host;//ip：127.0.0.1
    private int port;//port：6379
    private int timeout;//最大连接时间
    private String password;
    private int poolMaxTotal;//资源池最大连接数
    private int poolMaxldle;//资源池允许最大的空闲连接数
    private int poolMaxWait;//当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPoolMaxTotal() {
        return poolMaxTotal;
    }
    public void setPoolMaxTotal(int poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }
    public int getPoolMaxldle() {
        return poolMaxldle;
    }
    public void setPoolMaxldle(int poolMaxldle) {
        this.poolMaxldle = poolMaxldle;
    }
    public int getPoolMaxWait() {
        return poolMaxWait;
    }
    public void setPoolMaxWait(int poolMaxWait) {
        this.poolMaxWait = poolMaxWait;
    }


}
