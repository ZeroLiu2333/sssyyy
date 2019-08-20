package com.ithem.lxcq.system.dao;

import org.apache.ibatis.annotations.*;
import com.ithem.lxcq.system.domain.course;
import org.springframework.stereotype.Service;
//import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CourseDao {
    //获取所有课程
    @Select("select *  from course")
    public List<course> getAllCourse();
    //分页
    @Select("select * from course limit #{pageIndex},#{records}")
    public List<course> getCourse(@Param("pageIndex") int pageIndex, @Param("records")int records);
    //取页数
    @Select("select count(*) from course")
    public int getPage();
    @Select("select * from course where teacher=#{teacher} limit #{pageIndex},#{records}")
    public List<course> getTCourse(@Param("pageIndex") int pageIndex, @Param("records")int records,@Param("teacher") String teacher);
    @Select("select count(*) from course where slimit=#{slimit}")
    public int getSPage(@Param("slimit")String slimit);
    @Select("select * from course where slimit=#{slimit} limit #{pageIndex},#{records}")
    public List<course> getSCourse(@Param("pageIndex") int pageIndex, @Param("records")int records,@Param("slimit") String slimit);
    @Select("select count(*) from course where teacher=#{teacher}")
    public int getTPage(@Param("teacher")String teacher);
    @Select("select * from course where cid=#{cid} limit #{pageIndex},#{records}")
    public List<course> getCCourse(@Param("pageIndex") int pageIndex, @Param("records")int records,@Param("cid") int cid);
    @Select("select count(*) from course where cid=#{cid}")
    public int getCPage(@Param("cid")int cid);
    @Select("select * from course where teacher=#{teacher}")
    public List<course> getByTeacher(@Param("teacher") String teacher);
    @Select("select * from course where slimit=#{slimit}")
    public List<course> getBySchool(@Param("slimit") String slimit);
    @Select("select * from course where cid=#{cid}")
    public course getByCid(@Param("cid") int cid);
    //添加选课
    @Insert("insert into course(cid,cname,teacher,number,slimit,glimit,selected)"+"values(#{course.cid},#{course.cname},#{course.teacher},#{course.number},#{course.slimit},#{course.glimit},#{course.selected})")
    public void addCourse(@Param("course") course Course);
    //修改选课
    @Update("update course set cid=#{course.cid},cname=#{course.cname},teacher=#{course.teacher},number=#{course.number},slimit=#{course.slimit},glimit=#{course.glimit},selected=#{course.selected} where cid=#{cid}")
    public void UpdateCouse(@Param("course") course Course,@Param("cid")int cid);
    //删除课程
    @Delete("delete from course where cid=#{cid}")
    public void DeCourse(@Param("cid") int cid);
    //选课
    @Update("update course set selected=selected+1 where cid=#{cid}")
    public void CrNumber(@Param("cid") int cid);
    //退课
    @Update("update course set selected=selected-1 where cid=#{cid}")
    public void DeNumber(@Param("cid") int cid);

}
