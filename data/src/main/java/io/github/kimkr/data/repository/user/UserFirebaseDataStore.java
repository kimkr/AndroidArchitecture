package io.github.kimkr.data.repository.user;

import com.androidhuman.rxfirebase2.auth.RxFirebaseAuth;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class UserFirebaseDataStore implements UserDataStore {

    @Inject
    FirebaseAuth auth;

    @Inject
    public UserFirebaseDataStore() {
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    @Override
    public Single<FirebaseUser> signUp(String email, String password) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password);
    }

    @Override
    public Single<FirebaseUser> signIn(String email, String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password);
    }

    @Override
    public Single<FirebaseUser> signInWithCredential(AuthCredential authCredential) {
        return RxFirebaseAuth.signInWithCredential(auth, authCredential);
    }

    @Override
    public Completable signOut() {
        return RxFirebaseAuth.signOut(auth);
    }

    @Override
    public Completable resetPassword(String email){
        return RxFirebaseAuth.sendPasswordResetEmail(auth, email);
    }
}
