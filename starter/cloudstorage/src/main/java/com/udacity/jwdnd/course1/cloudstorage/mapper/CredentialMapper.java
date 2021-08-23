package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> listAll();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> selectAll(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) " +
            " VALUES (#{url}, #{username}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer insert(Credential credential);

    @Update("UPDATE CREDENTIALS set url = #{url}, " +
            "username = #{username}, password=#{password} WHERE credentialid = #{credentialid} AND  userid = #{userId}")
    Integer update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id} AND  userid = #{user}")
    Integer delete(Integer id, Integer user);
}
