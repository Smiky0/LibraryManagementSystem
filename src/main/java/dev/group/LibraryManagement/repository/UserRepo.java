package dev.group.LibraryManagement.repository;

import dev.group.LibraryManagement.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername (String username);
}


