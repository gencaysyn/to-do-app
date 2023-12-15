package com.gencaysyn.todoapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodoListDTO {
    private Long id;
    private String name;
    private List<TaskDTO> tasks;
}
