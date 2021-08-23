package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    /**
     *   noteid INT PRIMARY KEY auto_increment,
     *     notetitle VARCHAR(20),
     *     notedescription VARCHAR (1000),
     *     userid INT,
     */
    private Integer noteid;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
