package io.github.kimkr.data.entity.user.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.data.entity.user.UserEntity;
import io.github.kimkr.domain.entity.User;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class UserEntityDataMapper {

    @Inject
    public UserEntityDataMapper() {
    }

    public User transform(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();
            user.setId(userEntity.getId());
            user.setNickname(userEntity.getNickname());
            user.setImage(userEntity.getImage());
            user.setApiToken(userEntity.getApiToken());
            user.setFirebaseToken(userEntity.getFirebaseToken());
            user.setCognitoIdToken(userEntity.getCognitoIdToken());
            user.setCognitoAccessToken(userEntity.getCognitoAccessToken());
            user.setCloudFrontPolicyKey(userEntity.getCloudFrontPolicyKey());
            user.setCloudFrontSignature(userEntity.getCloudFrontSignature());
            user.setCloudFrontKeyPairId(userEntity.getCloudFrontKeyPairId());
        }
        return user;
    }

    public List<User> transform(Collection<UserEntity> userEntityCollection) {
        final List<User> userList = new ArrayList<>(20);
        for (UserEntity userEntity : userEntityCollection) {
            final User user = transform(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}
