package io.github.kimkr.data.repository.user;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.domain.entity.User;

/**
 * Created by kkr on 2017. 11. 1..
 */

@Singleton
public class UserDataToDomainMapper {

    @Inject
    public UserDataToDomainMapper() {
    }

    /**
     * FirebaseUser -> User
     */
    public User transform(FirebaseUser firebaseUser) {
        User user = null;
        if (firebaseUser != null) {
            user = new User();
            user.setId(firebaseUser.getUid());
            user.setEmail(firebaseUser.getEmail());
            user.setName(firebaseUser.getDisplayName());
            user.setPhone(firebaseUser.getPhoneNumber());
            Uri photoUri = firebaseUser.getPhotoUrl();
            user.setPhoto(photoUri != null ? photoUri.getEncodedPath() : null);
            user.setProvider(firebaseUser.getProviderId());
            user.setVerified(firebaseUser.isEmailVerified());
        }
        return user;
    }

    public List<User> transform(Collection<FirebaseUser> firebaseUsers) {
        final List<User> users = new ArrayList<>(20);
        for (FirebaseUser firebaseUser : firebaseUsers) {
            final User user = transform(firebaseUser);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }
}
