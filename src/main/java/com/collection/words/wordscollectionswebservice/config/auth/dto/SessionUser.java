package com.collection.words.wordscollectionswebservice.config.auth.dto;


import com.collection.words.wordscollectionswebservice.domain.user.Role;
import com.collection.words.wordscollectionswebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String role;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRoleKey();
    }
}
