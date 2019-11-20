package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.bean.RegisterBean;
import com.sobri.app.model.entity.UserEntity;

import java.util.List;

public class IndexRepository {
    private UserEntity userEntity;

    @Inject
    public IndexRepository(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<String> Users() throws Exception {
        return this.userEntity.Users();
    }

    public boolean UserRegister(RegisterBean userRegisterBean) throws Exception {
        return this.userEntity.UserRegister(userRegisterBean);
    }

    public boolean UserIsExist(String email) throws Exception {
        return this.userEntity.UserIsExist(email);
    }
}