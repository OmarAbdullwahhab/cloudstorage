package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CREATE TABLE IF NOT EXISTS NOTES (
 *     noteid INT PRIMARY KEY auto_increment,
 *     notetitle VARCHAR(20),
 *     notedescription VARCHAR (1000),
 *     userid INT,
 *     foreign key (userid) references USERS(userid)
 * );
 */
@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> selectAll(int userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}," +
            " #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    Integer insert(Note note);

    @Update("UPDATE NOTES set notetitle = #{noteTitle}, " +
            "notedescription = #{noteDescription} WHERE noteid = #{noteid} AND  userid = #{userId}")
    Integer update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{id} AND  userid = #{user}")
    Integer delete(Integer id, Integer user);


}
