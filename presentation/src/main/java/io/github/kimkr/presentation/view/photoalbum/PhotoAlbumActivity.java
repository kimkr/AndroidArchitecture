package io.github.kimkr.presentation.view.photoalbum;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.github.kimkr.presentation.R;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoalbum);
    }
}
