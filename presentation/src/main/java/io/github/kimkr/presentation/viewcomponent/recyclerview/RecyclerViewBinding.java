package io.github.kimkr.presentation.viewcomponent.recyclerview;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.Collection;

import io.github.kimkr.presentation.R;

/**
 * Created by kkr on 2017. 10. 28..
 */

public class RecyclerViewBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"binding:columns",
            "binding:orientation",
            "binding:reverse",
            "binding:viewpager",
            "binding:dividerItemDecoration",
            "binding:adapter",
            "binding:scrollListener"}, requireAll = false)
    public static void setListAdapter(RecyclerView recyclerView,
                                      int columns,
                                      int orientation,
                                      boolean reverse,
                                      boolean viewpager,
                                      Drawable drawable,
                                      RecyclerView.Adapter adapter,
                                      RecyclerView.OnScrollListener scrollListener) {
        Context context = recyclerView.getContext();
        // LAYOUT MANAGER, AS VIEWPAGER, ITEM DECORATION
        if (columns > 1) {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columns));
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, orientation, false);
            if (viewpager) {
                recyclerView.setLayoutManager(layoutManager);
                SnapHelper snapHelper = new PagerSnapHelper();
                recyclerView.setOnFlingListener(null);
                snapHelper.attachToRecyclerView(recyclerView);
            } else {
                layoutManager.setAutoMeasureEnabled(true);
                layoutManager.setStackFromEnd(reverse);
                recyclerView.setLayoutManager(layoutManager);
            }
            if (drawable != null) {
                Boolean decoratorSet = (Boolean) recyclerView.getTag(R.string.all_tag_flag);
                if (decoratorSet == null || !decoratorSet) {
                    DividerItemDecoration itemDecorator =
                            new DividerItemDecoration(recyclerView.getContext(), orientation);
                    recyclerView.addItemDecoration(itemDecorator);
                    recyclerView.setTag(R.string.all_tag_flag, true);
                }
            }
        }
        // ADAPTER
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        if (scrollListener != null) {
            recyclerView.addOnScrollListener(scrollListener);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("binding:items")
    public static void setItems(RecyclerView recyclerView, Collection items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (adapter instanceof BaseDataBindingAdapter) {
            ((BaseDataBindingAdapter) adapter).setItems(items);
        }
    }
}
