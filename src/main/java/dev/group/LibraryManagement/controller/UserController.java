package dev.group.LibraryManagement.controller;

import dev.group.LibraryManagement.entity.UserEntity;
import dev.group.LibraryManagement.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<UserEntity> getAllUser(){
        return userServices.getAll();
    }
    @GetMapping("/{userName}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String userName){
        try {
            UserEntity userFound = userServices.findByUserName(userName);
            if (userFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("Couldn't get user Error: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity newUser){
        try{
            UserEntity userFound = userServices.findByUserName(newUser.getUserName());
            if(userFound == null) {
                UserEntity userAdded = userServices.saveUser(newUser);
                return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            System.out.println("Couldn't post Error: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    update user details
    @PutMapping("/{userName}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity updatedUser, @PathVariable String userName){
        try{
            UserEntity userFound = userServices.findByUserName(userName);
            if(userFound == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userFound.setUserName(updatedUser.getUserName());
            userFound.setUserPass(updatedUser.getUserPass());
            userServices.saveUser(userFound);
            return new ResponseEntity<>(userFound, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Couldn't update user: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    update user contribution
    @PutMapping("/contribute/{userName}")
    public ResponseEntity<UserEntity> updateContribution(@PathVariable String userName, @RequestBody UserEntity updatedValue){
        try{
            UserEntity userFound = userServices.findByUserName(userName);
            if(userFound == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userFound.setContribution(updatedValue.getContribution());
            userServices.saveUser(userFound);
            return new ResponseEntity<>(userFound, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Couldn't update user: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> deleteByID(@PathVariable String userID){
        try{
            userServices.deleteById(userID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            System.out.println("Error while deleting user: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAll(){
        try{
            userServices.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            System.out.println("Couldn't delete Error: "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}