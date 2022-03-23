package com.example.authjwtspringsecurity.controller;

import com.example.authjwtspringsecurity.dtos.RegisterForm;
import com.example.authjwtspringsecurity.entities.AppUser;
import com.example.authjwtspringsecurity.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AccountRestController {
    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm registerForm){
        if(!registerForm.getPassword().equals(registerForm.getRepassword())) throw new RuntimeException("You must confirm your password");

        AppUser user  = accountService.findUserByUsername(registerForm.getUsername());

       if(user != null) throw new RuntimeException("This user is already exist");

       AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
        accountService.saveUser(appUser);
        accountService.addRoleToUser(registerForm.getUsername(),"USER");
        return  appUser;
    }
}
