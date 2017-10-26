package io.github.kimkr.data.datasource.user;

import org.greenrobot.greendao.rx.RxDao;

import io.github.kimkr.data.datasource.SqliteDataSource;
import io.github.kimkr.domain.model.user.User;

/**
 * Created by kkr on 2017. 10. 21..
 */

public class UserLocalDataStore extends SqliteDataSource<User, String> {

    public UserLocalDataStore(RxDao<User, String> dao) {
        super(dao);
    }
}
