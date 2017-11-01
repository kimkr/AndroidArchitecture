package io.github.kimkr.presentation.view.navigation;

import android.content.Context;
import android.content.Intent;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.presentation.view.Constants;

/**
 * Created by kkr on 2017. 11. 1..
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToPhotoAlbum(Context context, @Nullable String id) {
        if (context != null) {
            Intent intent = new Intent();
            if (id != null) {
                intent.putExtra(Constants.EXTRA_ID, id);
            }
            context.startActivity(intent);
        }
    }
}
