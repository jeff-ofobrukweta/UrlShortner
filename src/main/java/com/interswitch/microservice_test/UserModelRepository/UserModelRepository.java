package com.interswitch.microservice_test.UserModelRepository;

import com.interswitch.microservice_test.Model.UserModel;
import java.util.Map;

public interface UserModelRepository {
    void save(UserModel userModel);
    Map<String,UserModel> findAll();
    UserModel findById(String id);
    void update(UserModel userModel);
    void delete(String id);
}
