package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.entity.UserEntity;

import java.util.List;

public class IndexRepository {
    private UserEntity userEntity;

    @Inject
    public IndexRepository(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<String> Index() {
        return this.userEntity.Users();
    }
}
