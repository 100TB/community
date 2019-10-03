package com.example.comm.mapper;


import com.example.comm.DTO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatarUrl) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{avatarUrl})")
      void add(User user);

    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String value);
    @Select("select * from user where id=#{id}")
    User findUserByID(@Param("id")int id);

    @Select("select * from user where account_id=#{id}")
    User findUserByAccount_id(@Param("id")String account_id);

    @Update("update user set token=#{token}")
     void updateuserAccount(@Param("token") String token);
}
