package dev.group.LibraryManagement.controller;

import dev.group.LibraryManagement.entity.UserEntity;
import dev.group.LibraryManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<UserEntity> getAllUser(){
        return userRepo.findAll();
    }
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity newUser){
        return userRepo.save(newUser);
    }
}
