package com.ithem.lxcq.system.service;

import com.ithem.lxcq.system.dao.SelectedDao;
import com.ithem.lxcq.system.domain.course;
import com.ithem.lxcq.system.domain.user;
import com.ithem.lxcq.system.redis.CourseKey;
import com.ithem.lxcq.system.redis.RedisService;
import com.ithem.lxcq.system.redis.SelectKey;
import com.ithem.lxcq.system.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ithem.lxcq.system.domain.selected;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelectedService {
    @Autowired
    SelectedDao selectedDao;
    @Autowired
    CourseService courseService;
    @Autowired
    RedisService redisService;
    public static Map<Integer,Boolean> localMap=new HashMap<Integer, Boolean>();
    //选课
    public void InsertCourse(selected Selected){
        System.out.println(Selected.getCid());
        selectedDao.InsertCourse(Selected);
        courseService.CrNumber(Selected.getCid());
    }
    //退课
    public void DeletCourse(int cid,int id){
        selectedDao.DeletCourse(cid,id);
        courseService.DeNumber(cid);
    }
    //选课情况查询
    public PageModel checked(int pageIndex,int records,int id){
        int totalRecords=selectedDao.getPage(id);
        PageModel page=new PageModel(pageIndex,totalRecords,records);
        List<course> list1=selectedDao.checked(page.getPageIndex(),records,id);
        page.setList(list1);
        return page;

    }

    public course getByCidAndId(int cid, int id) {
        return selectedDao.getByCidAndId(cid, id);
    }

    public void DeletByCid(int cid) {
        selectedDao.DeletByCid(cid);
    }
    //队列使用的方法
    public void select(user user, int cid) {
        redisService.decr(CourseKey.sck,cid+"");
        int number=redisService.get(CourseKey.sck,cid+"",int.class);
        if(number==0){
            localMap.put(cid,true);
        }else{
            localMap.put(cid,false);
        }
    }

    public long getResult(int id, course course1) {
        selected selected = new selected();
        selected.setCid(course1.getCid());
        selected.setCname(course1.getCname());
        selected.setId(id);
        //在缓存中存储选课情况
        redisService.set(SelectKey.sk,course1.getCid()+""+id,selected);
        InsertCourse(selected);//在数据库中插入选课信息
        courseService.CrNumber(course1.getCid());
        return 1;
    }
}
