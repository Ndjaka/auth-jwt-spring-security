package com.example.authjwtspringsecurity.service;

import com.example.authjwtspringsecurity.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;

    UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = accountService.findUserByUsername(username);
         if(user == null) new UsernameNotFoundException(username);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getAppRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
