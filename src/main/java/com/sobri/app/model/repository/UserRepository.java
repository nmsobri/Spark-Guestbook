package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.entity.UserEntity;

import java.util.Map;

public class UserRepository {
    private UserEntity userEntity;

    @Inject
    public UserRepository(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Map<String, String> User(String email) throws Exception {
        return this.userEntity.User(email);
    }

    public boolean changePassword(int userID, String password) throws Exception {
        return this.userEntity.changePassword(userID, password);
    }
}
