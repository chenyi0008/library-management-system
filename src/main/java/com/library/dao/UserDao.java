package com.library.dao;

import com.library.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from tbl_user where username = #{username} and password = #{password}")
    public User login(@Param("username") String username, @Param("password")String password);

    @Insert("insert into tbl_user (username,password) values (#{username},#{password})")
    public int register(@Param("username") String username,@Param("password") String password);

    @Select("select username from tbl_user where username = #{username}")
    public User judge(@Param("username") String username);




}
