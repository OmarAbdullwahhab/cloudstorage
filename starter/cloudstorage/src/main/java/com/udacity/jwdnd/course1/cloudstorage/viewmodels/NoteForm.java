package com.udacity.jwdnd.course1.cloudstorage.viewmodels;

public class NoteForm {

    private Integer noteid;
    private String title;
    private String description;
    private Integer userId;

    public NoteForm()
    {

    }


    public NoteForm(Integer noteId,String title, String description, Integer userId)
    {
        this.noteid = noteId;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }


    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
