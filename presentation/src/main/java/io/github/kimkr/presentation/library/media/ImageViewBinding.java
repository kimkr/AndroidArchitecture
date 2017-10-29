package io.github.kimkr.presentation.library.media;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.widget.ImageView;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kkr on 2017. 10. 28..
 */

@Singleton
public class ImageViewBinding {

    private static Context context;

    @Inject
    public ImageViewBinding(Context context) {
        this.context = context;
    }

    @BindingConversion
    public static MediaType convertMimeToMediaType(String mime) {
        if (mime == null || mime.isEmpty()) {
            return MediaType.NA;
        }
        if (mime.contains("video/")) {
            return MediaType.VIDEO;
        } else if (mime.contains("/gif")) {
            return MediaType.GIF;
        } else if (mime.contains("audio/")) {
            return MediaType.AUDIO;
        } else if (mime.contains("image/")) {
            return MediaType.IMAGE;
        }
        return MediaType.NA;
    }

    @BindingAdapter({"android:src", "binding:type"})
    public static void setImageResource(ImageView imageView, String source, MediaType mediaType) {
        switch (mediaType) {
            case IMAGE:
                GlideApp.with(context)
                        .load(source)
                        .into(imageView);
                break;
            case GIF:
                GlideApp.with(context)
                        .asGif()
                        .load(source)
                        .into(imageView);
                break;
            case VIDEO:
                GlideApp.with(context)
                        .load(source)
                        .into(imageView);
                break;
            case AUDIO:
                GlideApp.with(context)
                        .load(source)
                        .into(imageView);
                break;
        }
    }
}
