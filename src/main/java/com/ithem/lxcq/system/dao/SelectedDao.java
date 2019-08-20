package com.ithem.lxcq.system.dao;

import com.ithem.lxcq.system.domain.course;
import org.apache.ibatis.annotations.*;
import com.ithem.lxcq.system.domain.selected;

import java.util.List;

@Mapper
public interface SelectedDao {
    @Insert("insert into selected (id,cname,cid) values(#{Selected.id},#{Selected.cname},#{Selected.cid})")
    public void InsertCourse(@Param("Selected") selected Selected);
    @Delete("delete from selected where cid=#{cid} and id=#{id}")
    public void DeletCourse(@Param("cid") int cid,@Param("id") int id);
    @Select("SELECT s.cid,s.cname,c.glimit,c.teacher,c.slimit,c.selected,c.number FROM selected s,course c\n" +
            "WHERE s.cid = c.cid AND id=#{id} limit #{pageIndex},#{records}")
    public List<course> checked(@Param("pageIndex")int pageIndex,@Param("records") int records,@Param("id") int id);
    @Select("select count(*) from selected where id=#{id}")
    public int getPage(@Param("id")int id);
    @Select("select * from selected where cid=#{cid} and id=#{id}")
    public course getByCidAndId(@Param("cid") int cid, @Param("id") int id);
    @Delete("delete from selected where cid=#{cid}")
    public void DeletByCid(@Param("cid") int cid);
}
