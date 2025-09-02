package com.NotesApp.NotesApp.service;

import com.NotesApp.NotesApp.model.UserData;
import com.NotesApp.NotesApp.model.UsersLoginDetails;
import com.NotesApp.NotesApp.repo.UserDataRepo;
import com.NotesApp.NotesApp.repo.UserLoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    UserLoginDetailsRepo userLoginDetailsRepo;
    @Autowired
    UserDataRepo userDataRepo;

    public UsersLoginDetails addUserLoginDetails(UsersLoginDetails usersLoginDetails) {
        try {
            if(userDataRepo.findByUsername(usersLoginDetails.getUsername()).isPresent() ){
                return null;
            }
            UsersLoginDetails savedUser = userLoginDetailsRepo.save(usersLoginDetails);

            UserData userData = new UserData();
            userData.setUserId(savedUser.getId());
            userData.setUsername(savedUser.getUsername());
            userData.setEmail(savedUser.getEmail());
            userDataRepo.save(userData);

            return savedUser;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<UsersLoginDetails> verifyUser(UsersLoginDetails usersLoginDetails) {
        Optional<UsersLoginDetails> user1= userLoginDetailsRepo.findByUsername(usersLoginDetails.getUsername());
        if(user1.isPresent() && user1.get().getPassword().equals(usersLoginDetails.getPassword())){
            return user1;
        }
        return Optional.empty();
    }

}
