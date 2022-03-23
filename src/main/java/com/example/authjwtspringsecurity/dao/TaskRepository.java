package com.example.authjwtspringsecurity.dao;

import com.example.authjwtspringsecurity.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}