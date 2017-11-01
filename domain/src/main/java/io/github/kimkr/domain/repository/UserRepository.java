package io.github.kimkr.domain.repository;

import io.github.kimkr.domain.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserRepository<T> {

    Single<User> getCurrentUser();

    Single<User> signUp(String email, String pwd);

    Single<User> signIn(String email, String pwd);

    Single<User> signInWithCredential(T credential);

    Completable signOut();

    Completable resetPassword(String email);
}
