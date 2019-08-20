package com.ithem.lxcq.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import com.ithem.lxcq.system.domain.userinformation;
@Mapper
public interface UserInfoDao {
    @Select("select * from userinformation where username=#{username}")
    public userinformation getInfo(@Param("username") String username);
}
