package io.github.kimkr.data.repository.content;

import android.content.Context;
import android.net.Uri;

import org.greenrobot.greendao.database.Database;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.entity.ContentEntity;
import io.github.kimkr.data.entity.DaoMaster;
import io.github.kimkr.data.entity.DaoSession;
import io.github.kimkr.data.repository.RxDao;
import io.github.kimkr.domain.repository.ContentRepository;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class ContentRepositoryModule {

    @Provides
    @Singleton
    DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "kimkr_db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    @Singleton
    @Named("app_content_uri")
    Uri provideContentUri(Context context) {
        return Uri.parse("content://media/external/file");
    }

    @Provides
    @Singleton
    RxDao<ContentEntity, Long> provideContentDao(DaoSession daoSession) {
        return new RxDao<>(daoSession.getContentEntityDao());
    }

    @Provides
    @Singleton
    ContentRepository provideContentRepository(ContentDataRepository contentDataRepository) {
        return contentDataRepository;
    }
}
