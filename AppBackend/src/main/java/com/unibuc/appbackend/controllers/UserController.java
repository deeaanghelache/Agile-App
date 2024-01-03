package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
//    pune api operation pt swagger
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") String id, @RequestBody String password){
        userService.changePassword(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
