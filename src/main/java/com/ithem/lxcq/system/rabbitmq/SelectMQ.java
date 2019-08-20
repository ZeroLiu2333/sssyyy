package com.ithem.lxcq.system.rabbitmq;
import com.ithem.lxcq.system.domain.user;

public class SelectMQ {
    private user user;
    private int cid;

    public com.ithem.lxcq.system.domain.user getUser() {
        return user;
    }

    public void setUser(com.ithem.lxcq.system.domain.user user) {
        this.user = user;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
