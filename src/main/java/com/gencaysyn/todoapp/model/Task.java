package com.gencaysyn.todoapp.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todolist_id")
    private TodoList todoList;

}
