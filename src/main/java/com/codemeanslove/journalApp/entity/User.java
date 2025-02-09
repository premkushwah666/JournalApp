package com.codemeanslove.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")// name of Document(~table)
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)  //<-- ye automatic create nhi hoga hme command chalani padegi
    // hame properties class me add krna padega ye -> spring.data.mongodb.auto-index-creation=true
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    //NULL nhi hogi [] empty hogi because of - new ArrayList k
    private List<String> roles;

}
