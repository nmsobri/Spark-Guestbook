package com.sobri.app.model.repository;

import com.google.inject.Inject;
import com.sobri.app.model.bean.RegisterBean;
import com.sobri.app.model.entity.UserEntity;

import java.util.List;
import java.util.Map;

public class IndexRepository {
    private UserEntity userEntity;

    @Inject
    public IndexRepository(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<String> Users() throws Exception {
        return this.userEntity.Users();
    }

    public Map<String, String> User(String email) throws Exception {
        return this.userEntity.User(email);
    }

    public boolean UserRegister(RegisterBean userRegisterBean) throws Exception {
        return this.userEntity.UserRegister(userRegisterBean);
    }

    public boolean UserIsExist(String email) throws Exception {
        return this.userEntity.UserIsExist(email);
    }

    public boolean saveComment(int user_id, String content) throws Exception {
        return this.userEntity.saveComment(user_id, content);
    }

    public List<Map<String, String>> Comments(int start, int limit) throws Exception {
        return this.userEntity.Comments(start, limit);
    }
}