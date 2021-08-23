package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByName(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{id}")
    User getUserById(int id);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    Integer insert(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    Integer delete(String username);
}
