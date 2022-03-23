package com.example.authjwtspringsecurity.service;

import com.example.authjwtspringsecurity.entities.AppRole;
import com.example.authjwtspringsecurity.entities.AppUser;

import java.util.Optional;

public interface AccountService {

    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username,String roleName);
    public AppUser findUserByUsername(String username);
}
