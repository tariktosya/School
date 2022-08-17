package com.example.proje.security.concretes;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.proje.repository.UserRepository;
import com.example.proje.model.entity.User;

@Service
@Slf4j
public class UserDetailsServiceManager implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        return new UserDetailsManager(user);
    }
}