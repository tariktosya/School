package com.example.proje.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.proje.business.abstracts.UserService;
import com.example.proje.model.request.user.UserAddDto;
import com.example.proje.model.request.user.UserChangePasswordDto;
import com.example.proje.model.request.user.UserLoginRequestDto;
import com.example.proje.model.request.user.UserUpdateDto;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody UserAddDto userAddDto) {
        return ResponseEntity.ok(userService.singup(userAddDto));
    }

    @PostMapping("/login")
    @CacheEvict("currentUser")
    public ResponseEntity<?> logIn(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return ResponseEntity.ok(userService.login(userLoginRequestDto));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody UserChangePasswordDto userChangePasswordDto) {
        return ResponseEntity.ok(userService.changePassword(userChangePasswordDto));
    }

    @DeleteMapping("/deleteUser{userId}")
    public ResponseEntity<?> deleteUser(@RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/updateUser{userId}")
    public ResponseEntity<?> updateUser(@RequestParam("userId") Integer userId, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateDto));
    }
}
