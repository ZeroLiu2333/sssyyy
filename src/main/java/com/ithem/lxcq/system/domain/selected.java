package com.ithem.lxcq.system.domain;

public class selected {
    int id;
    String cname;
    int cid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "selected{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", cid=" + cid +
                '}';
    }
}
