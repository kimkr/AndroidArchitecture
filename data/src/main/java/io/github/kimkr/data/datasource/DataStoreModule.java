package io.github.kimkr.data.datasource;

import android.content.Context;
import android.net.Uri;

import org.greenrobot.greendao.database.Database;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.entity.content.ContentDao;
import io.github.kimkr.data.entity.content.DaoMaster;
import io.github.kimkr.data.entity.content.DaoSession;
import io.github.kimkr.data.entity.user.UserDao;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class DataStoreModule {

    @Provides
    @Singleton
    DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "kimkr_db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    @Singleton
    ContentDao provideContentDao(DaoSession daoSession) {
        return daoSession.getContentDao();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(DaoSession daoSession) {
        return daoSession.getUserDao();
    }

    @Provides
    @Singleton
    @Named("app_content_uri")
    Uri provideContentUri(Context context) {
        return Uri.parse("content://media/external/file");
    }
}
