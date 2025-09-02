package com.NotesApp.NotesApp.repo;

import com.NotesApp.NotesApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {

    Optional<Note> findByUsernameAndTitle(String username, String title);

    Optional<List<Note>> findByUserId(Integer id);
}
