package com.gary.springsecurityjwt.service;

import com.gary.springsecurityjwt.entity.User;
import com.gary.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = repository.findByUserName(userName);
        if(user != null) {
            return new org.springframework.security.core.userdetails
                    .User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }
        return null;
    }
}
