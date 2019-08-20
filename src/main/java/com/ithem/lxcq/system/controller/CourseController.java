package com.ithem.lxcq.system.controller;

import com.ithem.lxcq.system.domain.user;
import com.ithem.lxcq.system.redis.CourseKey;
import com.ithem.lxcq.system.redis.RedisService;
import com.ithem.lxcq.system.result.CodeMsg;
import com.ithem.lxcq.system.result.Result;
import com.ithem.lxcq.system.service.CourseService;
import com.ithem.lxcq.system.service.SelectUserService;
import com.ithem.lxcq.system.service.SelectedService;
import com.ithem.lxcq.system.service.UserService;
import com.ithem.lxcq.system.utils.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ithem.lxcq.system.domain.course;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;
import com.ithem.lxcq.system.utils.PageModel;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;



@Controller
@RequestMapping("/checkcourse")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    SelectUserService selectUserService;
    @Autowired
    SelectedService selectedService;
    @Autowired
    RedisService redisService;

    course Course=new course();
    //第一次查询得到课程信息
    @RequestMapping(value="/usertype",params = "num",produces = "text/html")
    public String UserType(user user, HttpServletRequest request, Map map) throws Exception{
        //flag标志是页面是否显示选课和退课控件的标志
        int flag=0;
        map.put("flag",flag);
        int num=Integer.parseInt(request.getParameter("num"));
        int Records=10;
        String url="/checkcourse/usertype";
        PageModel page=courseService.getCourse(num,Records);
        page.setUrl(url);
        //分页信息
        map.put("page", page);
        map.put("user",user);
        System.out.println(page.getList());
        List<course> list=page.getList();
        map.put("course_list1",list);
        /*
        页面缓存
         */
        //取缓存

        if (user.getIp()==0){
            return "studentsystem";
        }else {

            return "teasystem";

        }

    }
    @RequestMapping(value = "/check",params = "teacher")
    @ResponseBody
    public Result<List> check(HttpServletRequest request){
        String teacher=request.getParameter("teacher");
        List<course> list=courseService.getByTeacher(teacher);
        if(list!=null){
            return Result.success(list);
        }
        else{
            return Result.error(CodeMsg.userEmpet);
        }
    }
    //搜索教师名字
    @RequestMapping( "/checkteacher")
    public String checkteacher(HttpServletRequest request,Map map,user user){
        int flag=0;
        map.put("flag",flag);
        String teacher=request.getParameter("teacher");
        List<course> list1=courseService.getByTeacher(teacher);
        map.put("course_list1",list1);
        int num=Integer.parseInt(request.getParameter("num"));
        int Records=10;
        //PageModel page=new PageModel();
        String url="/checkcourse/checkteacher";
        PageModel page=courseService.getTCourse(num,Records,teacher);
        page.setUrl(url);
        //分页信息
        map.put("user",user);
        map.put("page", page);
        if(user.getIp()==0)
        return "studentsystem";
        else {
            return "teasystem";
        }
    }
    @RequestMapping(value = "/check1",params = "slimit")
    @ResponseBody
    public Result<List> check1(HttpServletRequest request){
        String slimit=request.getParameter("slimit");
        List<course> list=courseService.getBySchool(slimit);
        if(list!=null){
            return Result.success(list);
        }
        else{
            return Result.error(CodeMsg.userEmpet);
        }
    }
    //搜素学院名称
    @RequestMapping("/checkschool")
    public String checkSchool(Map map,HttpServletRequest request,user user){
        int flag=0;
        map.put("flag",flag);
        String slimit=request.getParameter("slimit");
        List<course> list=courseService.getBySchool(slimit);
        map.put("course_list1",list);
        int num=Integer.parseInt(request.getParameter("num"));
        int Records=10;
        String url="/checkcourse/checkschool";
        PageModel page=courseService.getSCourse(num,Records,slimit);
        page.setUrl(url);
        //分页信息
       map.put("page", page);
       map.put("user",user);
        System.out.println(page.getList());
        System.out.println(list);
        if(user.getIp()==0)
            return "studentsystem";
        else {
            return "teasystem";
        }
    }
    @RequestMapping(value = "/check2",params = "cid")
    @ResponseBody
    public Result<course> check2(HttpServletRequest request){
        int cid=Integer.parseInt(request.getParameter("cid"));
        course course1=courseService.getByCid(cid);
        if(course1!=null){
            return Result.success(course1);
        }
        else{
            return Result.error(CodeMsg.userEmpet);
        }
    }
    //搜索课程编号
    @RequestMapping("/checkcid")
    public String checkCid(Map map,HttpServletRequest request,user user){
        int flag=0;
        map.put("flag",flag);
        int cid=Integer.parseInt(request.getParameter("cid"));
        course list=courseService.getByCid(cid);
        map.put("course_list1",list);
        int num=Integer.parseInt(request.getParameter("num"));
        int Records=10;
        //PageModel page=new PageModel();
        String url="/checkcourse/checkcid";
        PageModel page=courseService.getCCourse(num,Records,cid);
        page.setUrl(url);
        //分页信息
        map.put("page", page);
        map.put("user",user);
        System.out.println(page.getList());
        System.out.println(list);
        if(user.getIp()==0)
            return "studentsystem";
        else {
            return "teasystem";
        }
    }
    //教师
    //添加
    @RequestMapping("/add")
    @ResponseBody
    public Result<course> addCourse(HttpServletRequest request,Map map){
        int cid=Integer.parseInt(request.getParameter("cid"));
        int number=Integer.parseInt(request.getParameter("number"));
        Course.setCid(cid);
        Course.setCname(request.getParameter("cname"));
        Course.setTeacher(request.getParameter("teacher"));
        Course.setNumber(number);
        Course.setSlimit(request.getParameter("slimit"));
        Course.setGlimit(request.getParameter("glimit"));
        Course.setSelected(0);
        courseService.addCourse(Course);
            return Result.error(CodeMsg.success);
    }
    //删除课程
    @RequestMapping("/DeCourse")
    public String DeCourse(HttpServletRequest request,Map map){
        course Course=new course();
        int cid=Integer.parseInt(request.getParameter("cid"));
        /*
        删除课程不仅要删除课程表的课程，也要删除选课的课程，不然会造成选课表的冗余。

         */
        courseService.DeCourse(cid);
        selectedService.DeletByCid(cid);
        return "/checkcourse/usertype?num=1";
    }
    //修改课程
    @RequestMapping("/UpdateCouse")
    @ResponseBody
    public Result<course> UpdateCourse(HttpServletRequest request, Map map){
        int cid=Integer.parseInt(request.getParameter("cid"));
        int number=Integer.parseInt(request.getParameter("number"));
        int gcid=Integer.parseInt(request.getParameter("gcid"));
        Course.setCid(cid);
        Course.setCname(request.getParameter("cname"));
        Course.setTeacher(request.getParameter("teacher"));
        Course.setNumber(number);
        Course.setSlimit(request.getParameter("slimit"));
        Course.setGlimit(request.getParameter("glimit"));
        Course.setSelected(0);
        courseService.UpdateCourse(Course,gcid);
        return Result.error(CodeMsg.success);
    }
    //分页
    @RequestMapping("/totalpage")
    public void getPage(Map map){
        int pagetotal=courseService.getPage();
        map.put("total",pagetotal);
    }

}
