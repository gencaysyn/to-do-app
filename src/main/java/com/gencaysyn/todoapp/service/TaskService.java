package com.gencaysyn.todoapp.service;

import com.gencaysyn.todoapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gencaysyn.todoapp.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getTasks() {
        return  taskRepository.findAll();
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }
}
