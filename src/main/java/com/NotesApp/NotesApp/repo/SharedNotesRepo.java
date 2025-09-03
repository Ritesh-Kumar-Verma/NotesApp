package com.NotesApp.NotesApp.repo;


import com.NotesApp.NotesApp.model.SharedNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharedNotesRepo extends JpaRepository<SharedNotes , Integer> {


    Optional<SharedNotes> findBySharedByAndTitle(String username, String title);
}
