package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.viewmodels.NoteForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteServiceTests {

    @Autowired
    private NoteService noteService;

    private static NoteForm frm;

    @BeforeAll
    public static void initForm()
    {
        frm = new NoteForm();
        frm.setTitle("Simple Note");
        frm.setDescription("Simple Note Description");
        frm.setUserId(1);
    }
    @BeforeEach
    public void setup()
    {
        Integer id = this.noteService.addNote(frm);
        frm.setNoteid(id);
        System.out.println("note id = " + frm.getNoteid());

    }

    @AfterEach
    public void cleanup()
    {
        this.noteService.deleteNote(frm.getNoteid(),frm.getUserId());
    }

    @Test
    public void testGetAllNotes()
    {
        var items = this.noteService.getAllNotes(frm.getUserId());
        assertNotNull(items);
        assertNotEquals(0,items.size());

    }

    @Test
    public void testUpdateNote()
    {
        frm.setTitle("Updated Note");
        var found = this.noteService.updateNote(frm);
        assertTrue(found > 0);

    }
}
