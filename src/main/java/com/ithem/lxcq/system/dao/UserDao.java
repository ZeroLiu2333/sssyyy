package com.ithem.lxcq.system.dao;

import com.ithem.lxcq.system.domain.user;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from user where id=#{id}")
    public user getById(@Param("id") int id);
    @Update("update user set password=#{user.password} where id=#{user.id}")
    public void UpdatePassword(@Param("user") user user);
    @Select("select * from user where username=#{username} and password=#{password}")
    public user getByName(@Param("username") String username,@Param("password") String password);
}
