package dev.group.LibraryManagement.services;

import dev.group.LibraryManagement.entity.UserEntity;
import dev.group.LibraryManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getAll(){
        return userRepo.findAll();
    }
    public UserEntity findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
    public UserEntity saveUser (UserEntity user){
        return userRepo.save(user);
    }
    public void deleteById(String user){
        userRepo.deleteById(user);
    }
    public void deleteAll(){
        userRepo.deleteAll();
    }
}
