package com.gencaysyn.todoapp.service;

import com.gencaysyn.todoapp.dto.TodoListDTO;
import com.gencaysyn.todoapp.model.Task;
import com.gencaysyn.todoapp.model.TodoList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gencaysyn.todoapp.repository.TodoListRepository;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private final TodoListRepository todoListRepository;
    @Autowired
    private TaskService taskService;

    public TodoListService(TodoListRepository todoListRepository, ModelMapper modelMapper) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList getTodoList(Long todoListId){
        return todoListRepository.findById(todoListId).orElse(null);
    }


    public List<Task> getAllTasksOfTodoList(Long todoListId){
        TodoList todoList = getTodoListFromRepository(todoListId);
        return todoList.getTasks();
    }

    public Task addTaskToTodoList(Long todoListId, Task task){
        task.setTodoList(getTodoListFromRepository(todoListId));
        return taskService.saveTask(task);
    }

    private TodoList getTodoListFromRepository(Long todoListId){
        return todoListRepository.findById(todoListId).orElse(null);
    }

    public TodoList saveTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }
}
