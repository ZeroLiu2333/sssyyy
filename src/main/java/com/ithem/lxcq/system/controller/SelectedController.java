package com.ithem.lxcq.system.controller;

import com.ithem.lxcq.system.dao.CourseDao;
import com.ithem.lxcq.system.rabbitmq.MQSender;
import com.ithem.lxcq.system.rabbitmq.SelectMQ;
import com.ithem.lxcq.system.redis.CourseKey;
import com.ithem.lxcq.system.redis.RedisService;
import com.ithem.lxcq.system.redis.SelectKey;
import com.ithem.lxcq.system.result.CodeMsg;
import com.ithem.lxcq.system.result.Result;
import com.ithem.lxcq.system.service.CourseService;
import com.ithem.lxcq.system.service.SelectedService;
import com.ithem.lxcq.system.utils.PageModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import com.ithem.lxcq.system.domain.selected;
import com.ithem.lxcq.system.domain.user;
import com.ithem.lxcq.system.domain.course;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/selected")
public class SelectedController  implements InitializingBean {
    @Autowired
    SelectedService selectedService;
    @Autowired
    CourseService courseService;
    //选课
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender mqSender;
    // 1.系统初始化，把商品库存数量加载到redis
    public void afterPropertiesSet() throws Exception {
        List<course> list=courseService.getAllCourse();
        for(course course :list){
            redisService.set(CourseKey.sck,course.getCid()+"",course.getNumber());
        }
    }
    @RequestMapping("/getResult")
    @ResponseBody
    public Result<Long> getResult(Map map,HttpServletRequest request,user user){
        int cid=Integer.parseInt(request.getParameter("cid"));
        course course1=courseService.getByCid(cid);
        long result=selectedService.getResult(user.getId(),course1);
        System.out.println("轮询 result："+result);
        return Result.success(result);

    }
    /*
       1.系统初始化，把商品库存数量加载到redis
       2.收到请求，redis减库存，库存不足，直接返回，否则进入3
       3.请求入队，立即返回排队中
       （异步下单）
       4.请求出队，减少库存
       5.客户端轮询，是否秒杀成功
        */
    @RequestMapping("/insert")
    @ResponseBody
    public Result<Integer> InsertCourse(Map map,HttpServletRequest request,user user){
        int cid=Integer.parseInt(request.getParameter("cid"));
        //先判断内存标记localMap值，避免重复访问数据库和缓存，库存不足的情况
        if(SelectedService.localMap.get(cid)!=null&&SelectedService.localMap.get(cid)==true){
            return Result.error(CodeMsg.NotFull);
        }
        int number=redisService.get(CourseKey.sck,cid+"",int.class);
        course course1=courseService.getByCid(cid);
        /*
        取缓存中选课情况，如果根据cid和id能获取选课，则可以判断为重复选课
         */
        selected course2=redisService.get(SelectKey.sk,cid+""+user.getId(),selected.class);
        //course course2=selectedService.getByCidAndId(cid,user.getId());//设置为缓存取值
        long start=course1.getStart_date().getTime();
        long end=course1.getEnd_date().getTime();
        long now=System.currentTimeMillis();
        //秒杀状态量
        int status=0;
        //秒杀倒计时
        int remainSeconds=0;
        if(start>now){//选课还没开始
            status=0;
            remainSeconds=(int)((start-end)/1000);
            return Result.error(CodeMsg.prepare);
        }else if(now>end){//选课结束
            status=2;
            remainSeconds=-1;
            return Result.error(CodeMsg.END);
        }else {//选课开始
            if(course1.getNumber()==course1.getSelected()){
                return Result.error(CodeMsg.NotFull);
            }else if (course2!=null){
                return Result.error(CodeMsg.Repeate);
            }
            else {
                status = 1;
                remainSeconds = 0;
                //插入队列，传送cid和user到队列信息中，在队列中选取方法减少redis的库存--轮询的第一步
                SelectMQ sm=new SelectMQ();
                sm.setCid(cid);
                sm.setUser(user);
                mqSender.sendSeleced(sm);
                return Result.success(0);
            }
        }



    }
    //退课
    @ResponseBody
    @RequestMapping(value = "/delet",params = {"cid"})
    public Result<selected> DeletCourse(HttpServletRequest request,user user){
        int cid=Integer.parseInt(request.getParameter("cid"));
        redisService.delete(SelectKey.sk,cid+""+user.getId());
        selectedService.DeletCourse(cid,user.getId());
        courseService.DeNumber(cid);
        return Result.error(CodeMsg.success);

    }
    @RequestMapping("/checked")
    public String checked(HttpServletRequest request,user user,Map map) {
        int flag=1;
        map.put("flag",flag);
        int id = user.getId();
        int num=Integer.parseInt(request.getParameter("num"));
        int Records=10;
        String url="/selected/checked";
        PageModel page=selectedService.checked(num,Records,id);
        page.setUrl(url);
        //分页信息
        map.put("page", page);
        map.put("course_list1",page.getList());
        map.put("user",user);
        System.out.println(page.getList());
        return "studentsystem";
    }
}
