package com.example.authjwtspringsecurity.dao;

import com.example.authjwtspringsecurity.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}