package com.example.authjwtspringsecurity.dao;

import com.example.authjwtspringsecurity.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

    public AppRole findByRoleName(String roleName);
}