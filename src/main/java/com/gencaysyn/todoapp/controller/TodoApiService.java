package com.gencaysyn.todoapp.controller;

import com.gencaysyn.todoapp.common.ApiResponse;
import com.gencaysyn.todoapp.dto.TaskDTO;
import com.gencaysyn.todoapp.dto.TodoListDTO;
import com.gencaysyn.todoapp.model.Task;
import com.gencaysyn.todoapp.model.TodoList;
import com.gencaysyn.todoapp.service.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/to-do-lists")
public class TodoApiService {
    @Autowired
    private TodoListService todoListService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{todoListId}/tasks")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTask(@PathVariable Long todoListId) {
        try {
            List<Task> tasks = todoListService.getAllTasksOfTodoList(todoListId);
            List<TaskDTO> taskDTOList = tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();
            return new ResponseEntity<>(ApiResponse.success("Tasks retrieved successfully", taskDTOList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.fail("Failed to retrieve tasks", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{todoListId}")
    public ResponseEntity<ApiResponse<TodoListDTO>> getTodoList(@PathVariable Long todoListId) {
        try {
            TodoList todoList = todoListService.getTodoList(todoListId);
            TodoListDTO todoListDTO = modelMapper.map(todoList, TodoListDTO.class);
            return new ResponseEntity<>(ApiResponse.success("Tasks retrieved successfully", todoListDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.fail("Failed to retrieve tasks", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{todoListId}/tasks")
    public ResponseEntity<ApiResponse<TaskDTO>> addTask(@PathVariable Long todoListId, @RequestBody TaskDTO taskDTO) {
        try {

            TaskDTO addedTask = modelMapper.map(todoListService.addTaskToTodoList(todoListId, modelMapper.map(taskDTO, Task.class)), TaskDTO.class);
            return new ResponseEntity<>(ApiResponse.success("Task added successfully", addedTask), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.fail("Failed to add task", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<TodoListDTO>> createTodoList(@RequestBody TodoListDTO todoListDTO) {
        todoListDTO.setId(null);
        TodoList createdTodoList = todoListService.saveTodoList(modelMapper.map(todoListDTO, TodoList.class));

        if (createdTodoList != null) {
            TodoListDTO createdTodoListDTO = modelMapper.map(createdTodoList, TodoListDTO.class);
            return new ResponseEntity<>(ApiResponse.success("TodoList created successfully", createdTodoListDTO), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(ApiResponse.fail("Failed to create TodoList"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
