package com.NotesApp.NotesApp.service;

import com.NotesApp.NotesApp.model.Note;
import com.NotesApp.NotesApp.model.UsersLoginDetails;
import com.NotesApp.NotesApp.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;


    public Note addNotes(Note note) {
        Optional<Note> noteFromRepo =  noteRepo.findByUsernameAndTitle(note.getUsername(),note.getTitle());
        if(noteFromRepo.isPresent()){
            noteFromRepo.get().setContent(note.getContent());
            noteRepo.save(noteFromRepo.get());
            return noteFromRepo.get();
        }
        else{
            return noteRepo.save(note);
        }

    }


    public Optional<Note> deleteNote(UsersLoginDetails user, Note note) {
        Optional<Note> n2 = noteRepo.findByUsernameAndTitle(user.getUsername(), note.getTitle());
        if (n2.isPresent()) {
            noteRepo.delete(n2.get());
            return n2;
        }
        return Optional.empty();
    }


    public List<Note> getNotes(Integer id) {
        Optional<List<Note>> list = noteRepo.findByUserId(id);
        return list.get();

    }
}
