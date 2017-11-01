package io.github.kimkr.data.repository.user;

import com.google.firebase.auth.AuthCredential;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.domain.entity.User;
import io.github.kimkr.domain.repository.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class UserDataRepository implements UserRepository<AuthCredential> {

    @Inject
    UserDataToDomainMapper firebaseUserMapper;
    @Inject
    UserFirebaseDataStore firebaseDataStore;

    @Inject
    public UserDataRepository() {
    }

    @Override
    public Single<User> getCurrentUser() {
        return Single.just(firebaseDataStore.getCurrentUser())
                .map(firebaseUserMapper::transform);
    }

    @Override
    public Single<User> signUp(String email, String pwd) {
        return firebaseDataStore.signUp(email, pwd)
                .map(firebaseUserMapper::transform);
    }

    @Override
    public Single<User> signIn(String email, String pwd) {
        return firebaseDataStore.signIn(email, pwd)
                .map(firebaseUserMapper::transform);
    }

    @Override
    public Single<User> signInWithCredential(AuthCredential credential) {
        return firebaseDataStore.signInWithCredential(credential)
                .map(firebaseUserMapper::transform);
    }

    @Override
    public Completable signOut() {
        return firebaseDataStore.signOut();
    }

    @Override
    public Completable resetPassword(String email) {
        return firebaseDataStore.resetPassword(email);
    }
}
