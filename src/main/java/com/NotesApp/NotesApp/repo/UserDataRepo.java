package com.NotesApp.NotesApp.repo;

import com.NotesApp.NotesApp.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepo extends JpaRepository<UserData , Integer> {

    Optional<UserData> findByUsername(String username);
}
