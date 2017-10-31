package io.github.kimkr.domain.repository;

import io.github.kimkr.domain.entity.User;
import io.reactivex.Single;

public interface UserRepository {

    Single<User> getCurrentUser();

    Single<User> signUp(String email, String pwd);

    Single<User> signIn(String email, String pwd);
}
