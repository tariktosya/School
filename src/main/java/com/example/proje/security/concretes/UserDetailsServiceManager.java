package com.example.proje.security.concretes;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.proje.dataAccess.UserDao;
import com.example.proje.model.entity.User;

@Service
@Slf4j
public class UserDetailsServiceManager implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userDao.findByEmail(email);
        return new UserDetailsManager(user);
    }
}