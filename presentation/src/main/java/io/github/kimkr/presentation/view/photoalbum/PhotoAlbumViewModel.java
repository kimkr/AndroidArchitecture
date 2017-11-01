package io.github.kimkr.presentation.view.photoalbum;

import android.Manifest;
import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.domain.entity.Content;
import io.github.kimkr.domain.repository.ContentRepository;
import io.github.kimkr.presentation.BR;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
                .filter(granted -> granted)
                .subscribe(granted -> loadItems()
                                .subscribe(items -> this.items.addAll(items),
                                        this::showError),
                        this::showError);
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
        Flowable.fromIterable(items)
                .filter(item -> item.getId().equals(startContent))
                .firstElement()
                .map(item -> item.getContent())
                .subscribe(content -> contentRepository.save(content)
                                .subscribe((saved) -> Timber.d("saving completed : %s", saved),
                                        Timber::e),
                        Timber::e);
        activityWeakReference.get().showViewer(startContent);
    }

    public Single<List<PhotoAlbumItemViewModel>> loadItems() {
        return contentRepository.getContents()
                .map(contents -> {
                    List<PhotoAlbumItemViewModel> itemViewModels = new ArrayList<>();
                    for (Content content : contents) {
                        itemViewModels.add(new PhotoAlbumItemViewModel(content));
                    }
                    return itemViewModels;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> gainStoragePermission() {
        RxPermissions permissions = new RxPermissions(activityWeakReference.get());
        return permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void showError(Throwable e) {
        activityWeakReference.get().showToast(e.getMessage());
    }
}
