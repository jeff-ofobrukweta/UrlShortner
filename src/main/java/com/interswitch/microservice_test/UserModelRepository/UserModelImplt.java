package com.interswitch.microservice_test.UserModelRepository;
import com.interswitch.microservice_test.Model.UserModel;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class UserModelImplt implements UserModelRepository {

    private RedisTemplate<String,UserModel> redisTemplate;
    private HashOperations hashOperations;

    public UserModelImplt(RedisTemplate<String, UserModel> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(UserModel userModel){ hashOperations.put("USER",userModel.getId(),userModel);}
    @Override
    public Map<String,UserModel> findAll(){ return hashOperations.entries("USER"); }
    @Override
    public UserModel findById(String id){return (UserModel)hashOperations.get("USER", id);}
    @Override
    public void update(UserModel userModel){save(userModel);}
    @Override
    public void delete(String id){hashOperations.delete("USER",id);}
}
