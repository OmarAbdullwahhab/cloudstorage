package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CREATE TABLE IF NOT EXISTS FILES (
 *     fileId INT PRIMARY KEY auto_increment,
 *     filename VARCHAR,
 *     contenttype VARCHAR,
 *     filesize VARCHAR,
 *     userid INT,
 *     filedata BLOB,
 *     foreign key (userid) references USERS(userid)
 * );
 */
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> selectAll(int userId);

    @Insert("INSERT INTO FILES (filename, contenttype,filesize, userid,filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{id} AND userid=#{user}")
    Integer delete(Integer id, Integer user);

    @Select("SELECT * FROM FILES WHERE fileId=#{id} AND userid=#{user}")
    File getFile(Integer id, Integer user);

}
