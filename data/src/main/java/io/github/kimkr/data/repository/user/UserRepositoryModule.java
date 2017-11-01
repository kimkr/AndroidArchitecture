package io.github.kimkr.data.repository.user;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.domain.repository.UserRepository;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Module
public class UserRepositoryModule {

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth(Context context) {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
