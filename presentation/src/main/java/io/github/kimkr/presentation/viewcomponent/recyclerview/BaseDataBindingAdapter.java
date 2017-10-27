package io.github.kimkr.presentation.viewcomponent.recyclerview;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

import io.github.kimkr.presentation.BR;

/**
 * Created by kkr on 2017. 10. 27..
 */

public abstract class BaseDataBindingAdapter<VM, ITEM_VM>
        extends RecyclerView.Adapter<BaseDataBindingViewHolder> {

    private static final Object DB_PAYLOAD = new Object();
    private final ItemChangedCallback itemChangedCallback;
    private VM viewModel;
    private ObservableList<ITEM_VM> items;
    private RecyclerView recyclerView;

    public BaseDataBindingAdapter() {
        super();
        this.itemChangedCallback = new ItemChangedCallback(this);
    }

    @LayoutRes
    public abstract int getItemLayoutId(int position);

    @Override
    @CallSuper
    public BaseDataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseDataBindingViewHolder vh = BaseDataBindingViewHolder.create(parent, viewType);
        vh.binding.addOnRebindCallback(onRebindCallback);
        return vh;
    }

    @Override
    public final void onBindViewHolder(BaseDataBindingViewHolder holder, int position,
                                       List<Object> payloads) {
        // when a VH is rebound to the same item, we don't have to call the setters
        if (payloads.isEmpty() || hasNonDataBindingInvalidate(payloads)) {
            bindItem(holder, position, payloads);
        }
        holder.binding.executePendingBindings();
    }

    @Override
    public final void onBindViewHolder(BaseDataBindingViewHolder holder, int position) {
        throw new IllegalArgumentException("just overridden to make final.");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = null;
        if (items != null) {
            items.removeOnListChangedCallback(itemChangedCallback);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutId(position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public ObservableList<ITEM_VM> getItems() {
        return items;
    }

    public void setItems(@Nullable Collection<ITEM_VM> items) {
        if (this.items == items) {
            return;
        }
        if (this.items != null) {
            this.items.removeOnListChangedCallback(itemChangedCallback);
            notifyItemRangeRemoved(0, this.items.size());
        }
        if (items instanceof ObservableList) {
            this.items = (ObservableList<ITEM_VM>) items;
            notifyItemRangeInserted(0, this.items.size());
            this.items.addOnListChangedCallback(itemChangedCallback);
        } else if (items != null) {
            this.items = new ObservableArrayList<>();
            this.items.addOnListChangedCallback(itemChangedCallback);
            this.items.addAll(items);
        } else {
            this.items = null;
        }
    }

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Override this method to handle binding your subTags into views
     *
     * @param holder   The ViewHolder that has the binding instance
     * @param position The position of the item in the adapter
     * @param payloads The payloads that were passed into the onBind method
     */
    protected void bindItem(BaseDataBindingViewHolder holder, int position,
                            List<Object> payloads) {
        holder.binding.setVariable(getItemVariableId(), getItem(position));
        if (viewModel != null) {
            holder.binding.setVariable(getViewModelVariableId(), viewModel);
        }
    }

    protected ITEM_VM getItem(int position) {
        if (items != null && getItemCount() > position) {
            return items.get(position);
        }
        return null;
    }

    protected int getItemVariableId() {
        return BR.itemViewModel;
    }

    protected int getViewModelVariableId() {
        return BR.viewModel;
    }

    private boolean hasNonDataBindingInvalidate(List<Object> payloads) {
        for (Object payload : payloads) {
            if (payload != DB_PAYLOAD) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is used to block subTags from updating themselves. RecyclerView wants to know when an
     * item is invalidated and it prefers to refresh it via onRebind. It also helps with performance
     * since data binding will not update views that are not changed.
     */
    private final OnRebindCallback onRebindCallback = new OnRebindCallback() {
        @Override
        public boolean onPreBind(ViewDataBinding binding) {
            if (recyclerView == null || recyclerView.isComputingLayout()) {
                return true;
            }
            int childAdapterPosition = recyclerView.getChildAdapterPosition(binding.getRoot());
            if (childAdapterPosition == RecyclerView.NO_POSITION) {
                return true;
            }
            notifyItemChanged(childAdapterPosition, DB_PAYLOAD);
            return false;
        }
    };
}
