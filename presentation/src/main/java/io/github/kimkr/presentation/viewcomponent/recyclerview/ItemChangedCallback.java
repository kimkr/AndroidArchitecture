package io.github.kimkr.presentation.viewcomponent.recyclerview;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * Created by kkr on 2017. 10. 27..
 */

public class ItemChangedCallback extends ObservableList.OnListChangedCallback {

    private final WeakReference<BaseDataBindingAdapter> adapterReference;

    public ItemChangedCallback(BaseDataBindingAdapter adapter) {
        this.adapterReference = new WeakReference<>(adapter);
    }

    @Override
    public void onChanged(ObservableList sender) {
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = adapterReference.get();
        if (adapter != null) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }
}
