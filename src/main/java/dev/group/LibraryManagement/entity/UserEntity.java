package dev.group.LibraryManagement.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class UserEntity {
    @Id
    private ObjectId userID;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String userPass;
    private String contribution;
}
