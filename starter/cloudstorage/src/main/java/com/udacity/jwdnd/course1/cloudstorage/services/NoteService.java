package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {


    private final NoteMapper noteMapper;

    public NoteService(@Autowired NoteMapper noteMapper)
    {
        this.noteMapper = noteMapper;
    }

    public List<NoteForm> getAllNotes(Integer userId)
    {
        List<Note> data = this.noteMapper.selectAll(userId);
        if(data == null)
            return null;
        var collected  = data.stream().map((x)-> this.fromNote(x)).collect(Collectors.toList());
        return collected;
    }

    public Integer addNote(NoteForm form)
    {
        Note note = fromNoteForm(form);
         this.noteMapper.insert(note);
         return note.getNoteid();
    }

    public Integer updateNote(NoteForm form)
    {
        Note note = fromNoteForm(form);
        return this.noteMapper.update(note);
    }

    public Integer deleteNote(Integer note, Integer user)
    {
        return this.noteMapper.delete(note,user);
    }

    private Note fromNoteForm(NoteForm form)
    {
        Note note = new Note();
        note.setNoteid(form.getNoteid());
        note.setNoteTitle(form.getTitle());
        note.setNoteDescription(form.getDescription());
        note.setUserId(form.getUserId());

        return note;
    }
    private NoteForm fromNote(Note x)
    {
        return new NoteForm(x.getNoteid(),
                x.getNoteTitle(),x.getNoteDescription(),x.getUserId());
    }
}
