package io.github.kimkr.presentation.view.photoalbum;

import android.Manifest;
import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.domain.repository.ContentRepository;
import io.github.kimkr.presentation.BR;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 26..
 */

@ActivityScope
public class PhotoAlbumViewModel extends BaseObservable implements DefaultLifecycleObserver {

    public final ObservableList<PhotoAlbumItemViewModel> items = new ObservableArrayList<>();
    ViewMode viewMode = ViewMode.LIST;
    @Inject
    WeakReference<PhotoAlbumActivity> activityWeakReference;
    @Inject
    ContentRepository contentRepository;

    @Inject
    public PhotoAlbumViewModel() {
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        gainStoragePermission()
                .flatMap(granted -> {
                    if (granted) {
                        return loadItems();
                    }
                    return Observable.error(new Exception("Failed to gain permission"));
                })
                .subscribe(items -> this.items.addAll(items),
                        e -> activityWeakReference.get().showToast(e.getMessage()));
    }

    @Bindable
    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
        notifyPropertyChanged(BR.viewMode);
    }

    public void startViewer(Long startContent) {
        // TEST
        Observable.from(items)
                .filter(item -> item.getId().equals(startContent))
                .first()
                .map(item -> item.getContent())
                .subscribe(content -> contentRepository.save(content)
                                .subscribe((saved) -> Timber.d("saving completed : %s", saved),
                                        Timber::e),
                        Timber::e);
        activityWeakReference.get().showViewer(startContent);
    }

    public Observable<List<PhotoAlbumItemViewModel>> loadItems() {
        return contentRepository.getContents()
                .toObservable()
                .flatMap(Observable::from)
                .map(PhotoAlbumItemViewModel::new)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> gainStoragePermission() {
        RxPermissions permissions = new RxPermissions(activityWeakReference.get());
        return permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}
