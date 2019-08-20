package com.ithem.lxcq.system.domain;

import java.util.Date;

public class course {
    private int cid;
    private String cname;
    private String teacher;
    private int number;
    private String slimit;
    private String glimit;
    private int selected;
    private Date startdate;

    public Date getStart_date() {
        return startdate;
    }

    public void setStart_date(Date start_date) {
        this.startdate = startdate;
    }

    public Date getEnd_date() {
        return enddate;
    }

    public void setEnd_date(Date end_date) {
        this.enddate = end_date;
    }

    private Date enddate;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSlimit() {
        return slimit;
    }

    public void setSlimit(String slimit) {
        this.slimit = slimit;
    }

    public String getGlimit() {
        return glimit;
    }

    public void setGlimit(String glimit) {
        this.glimit = glimit;
    }



    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", teacher='" + teacher + '\'' +
                ", number=" + number +
                ", slimit='" + slimit + '\'' +
                ", glimit='" + glimit + '\'' +
                ", selected=" + selected +
                ", start_date=" + startdate +
                ", end_date=" + enddate +
                '}';
    }
}
