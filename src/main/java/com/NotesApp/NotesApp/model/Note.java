package com.NotesApp.NotesApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String username;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

//    private String createdAt;
//
//    private String updatedAt;



}
