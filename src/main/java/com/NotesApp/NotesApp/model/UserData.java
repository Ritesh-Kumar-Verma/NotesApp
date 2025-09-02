package com.NotesApp.NotesApp.model;

import com.NotesApp.NotesApp.NotesAppApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;


    private String username;

    private String email;

//    private List<Note> notes;


}
