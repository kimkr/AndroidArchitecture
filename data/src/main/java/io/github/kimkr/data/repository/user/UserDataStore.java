package io.github.kimkr.data.repository.user;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kkr on 2017. 10. 31..
 */

public interface UserDataStore {

    FirebaseUser getCurrentUser();

    Single<FirebaseUser> signUp(String email, String pwd);

    Single<FirebaseUser> signIn(String email, String pwd);

    Single<FirebaseUser> signInWithCredential(AuthCredential authCredential);

    Completable signOut();

    Completable resetPassword(String email);
}
