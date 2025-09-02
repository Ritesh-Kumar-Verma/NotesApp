package com.NotesApp.NotesApp.controller;


import com.NotesApp.NotesApp.model.Note;
import com.NotesApp.NotesApp.model.UsersLoginDetails;
import com.NotesApp.NotesApp.model.Wrapper;
import com.NotesApp.NotesApp.service.NoteService;
import com.NotesApp.NotesApp.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class LoginController {



    @Autowired
    UserLoginService userLoginService;

    @Autowired
    NoteService noteService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }


    @PostMapping("/signup")
    public ResponseEntity<UsersLoginDetails> addNewUser(@RequestBody UsersLoginDetails usersLoginDetails){
        UsersLoginDetails user = userLoginService.addUserLoginDetails(usersLoginDetails);
        if(user == null){
            return new ResponseEntity<>(null , HttpStatus.NOT_ACCEPTABLE);
        }
        return  new ResponseEntity<>(user , HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<UsersLoginDetails> verifyUser(@RequestBody UsersLoginDetails usersLoginDetails){
        Optional<UsersLoginDetails> u1 = userLoginService.verifyUser(usersLoginDetails);
        if( u1.isPresent() ){
        return new ResponseEntity<>(u1.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(usersLoginDetails , HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @PostMapping("/addnotes")
    public ResponseEntity<Note> addNotes(@RequestBody Wrapper wrapper){
        UsersLoginDetails uld = wrapper.getUsersLoginDetails();
        Note note = wrapper.getNote();


        Optional<UsersLoginDetails> u1 = userLoginService.verifyUser(uld);
        if(u1.isPresent()){
            note.setUserId(u1.get().getId());
            Note note2= noteService.addNotes(note);
            return new ResponseEntity<>(note2 , HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/deletenote")
    public ResponseEntity<?> deleteNote(@RequestBody Wrapper wrapper){
        UsersLoginDetails uld = wrapper.getUsersLoginDetails();
        Note note = wrapper.getNote();


        Optional<UsersLoginDetails> u1 = userLoginService.verifyUser(uld);
        if(u1.isPresent()){
            Optional<Note> n2 = noteService.deleteNote(u1.get(),note);
            if(n2.isPresent()){
                return new ResponseEntity<>(n2.get(),HttpStatus.OK);
            }
            return new ResponseEntity<>("Note Not Found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Wrong credential",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/getnotes")
    public ResponseEntity<?> getNotes(@RequestBody UsersLoginDetails usersLoginDetails){
        Optional<UsersLoginDetails> user = userLoginService.verifyUser(usersLoginDetails);
        if(user.isPresent()){
            List<Note> list = noteService.getNotes(user.get().getId());
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
    }







}
