package com.ithem.lxcq.system.service;
import com.ithem.lxcq.system.dao.CourseDao;
import com.ithem.lxcq.system.domain.course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ithem.lxcq.system.utils.PageModel;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    //分页
    public int getPage(){
        return courseDao.getPage();
    }
    public PageModel getCourse(int pageIndex,int records){
        int totalRecords=courseDao.getPage();
        PageModel page=new PageModel(pageIndex,totalRecords,records);
        List<course> list=courseDao.getCourse(page.getPageIndex(),records);
        page.setList(list);
        return page;
    }
    public PageModel getTCourse(int num,int Records,String teacher){
        int totalRecords=courseDao.getTPage(teacher);
        PageModel page=new PageModel(num,totalRecords,Records);
        List<course> list=courseDao.getTCourse(page.getPageIndex(),Records,teacher);
        page.setList(list);
        return page;

    }
    public PageModel getSCourse(int num,int Records,String slimit){
        int totalRecords=courseDao.getSPage(slimit);
        PageModel page=new PageModel(num,totalRecords,Records);
        List<course> list=courseDao.getSCourse(page.getPageIndex(),Records,slimit);
        page.setList(list);
        return page;

    }
    public PageModel getCCourse(int num,int Records,int cid){
        int totalRecords=courseDao.getCPage(cid);
        PageModel page=new PageModel(num,totalRecords,Records);
        List<course> list=courseDao.getCCourse(page.getPageIndex(),Records,cid);
        page.setList(list);
        return page;

    }
    //查询功能
    public List<course> getByTeacher(String teacher){
        return courseDao.getByTeacher(teacher);
    }
    public List<course> getBySchool(String slimit){
        return courseDao.getBySchool(slimit);
    }
    public course getByCid(int cid){
        return courseDao.getByCid(cid);
    }
    //选课
    public void CrNumber(int id){
        courseDao.CrNumber(id);
    }
    //退课
    public void DeNumber(int id){
        courseDao.DeNumber(id);
    }
    //添加课程
    public void addCourse(course Course){
        courseDao.addCourse(Course);

    }
    //修改课程
    public void UpdateCourse(course course,int cid){
        courseDao.UpdateCouse(course,cid);
    }
    //删除课程
    public void DeCourse(int cid){
        courseDao.DeCourse(cid);
    }
    public List<course> getAllCourse(){
        return courseDao.getAllCourse();
    }
}
