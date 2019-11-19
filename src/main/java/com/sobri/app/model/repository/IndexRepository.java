package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.entity.UserEntity;

public class IndexRepository {
    private UserEntity userEntity;

    @Inject
    public IndexRepository(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String Index() {
        return this.userEntity.Index();
    }
}
