package com.NotesApp.NotesApp.repo;

import com.NotesApp.NotesApp.model.UsersLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserLoginDetailsRepo extends JpaRepository<UsersLoginDetails , Integer> {

    Optional<UsersLoginDetails> findByUsername(String username);


}
