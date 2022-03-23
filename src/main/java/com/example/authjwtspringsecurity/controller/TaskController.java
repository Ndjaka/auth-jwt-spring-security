package com.example.authjwtspringsecurity.controller;

import com.example.authjwtspringsecurity.dao.TaskRepository;
import com.example.authjwtspringsecurity.entities.Task;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> listTasks(){
        return  ResponseEntity.ok().body(taskRepository.findAll());
    }


    @PostMapping("/tasks")
    public ResponseEntity<Task> save(@RequestBody Task  k){
        return  ResponseEntity.status(CREATED).body(taskRepository.save(k));
    }


}
