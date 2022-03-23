package com.example.authjwtspringsecurity.service;

import com.example.authjwtspringsecurity.dao.RoleRepository;
import com.example.authjwtspringsecurity.dao.UserRepository;
import com.example.authjwtspringsecurity.entities.AppRole;
import com.example.authjwtspringsecurity.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AccountServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AppUser saveUser(AppUser user) {

        String hashPw = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUsername(username);
        user.getAppRoles().add(role);

    }

    @Override
    public AppUser findUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
