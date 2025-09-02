package com.NotesApp.NotesApp.controller;


import com.NotesApp.NotesApp.model.UsersLoginDetails;
import com.NotesApp.NotesApp.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class LoginController {



    @Autowired
    UserLoginService userLoginService;


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










//
//    @PostMapping("/addselflocation")
//    public ResponseEntity<String> updateSelfLocation(@RequestBody UserWrapper userWrapper){
//        UserData userData = userWrapper.getUserData();
//        UsersLoginDetails usersLoginDetails = userWrapper.getUsersLoginDetails();
//
//
//        UsersLoginDetails u2 = userLoginService.verifyUser(usersLoginDetails);
//        if( u2 != null){
//            userData.setId(u2.getId());
//
//            UserData u1 = userServices.updateSelfLocation(userData);
//            if(u1 != null){
//                return new ResponseEntity<>("Successfully Updated" , HttpStatus.OK);
//            }
//            return new ResponseEntity<>("UserExistButErrorInUpdating",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>("UserDonotExist", HttpStatus.NOT_ACCEPTABLE);
//
//    }



}
