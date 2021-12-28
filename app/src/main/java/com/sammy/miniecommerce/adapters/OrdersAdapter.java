package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sammy.miniecommerce.databinding.OrderListItemBinding;
import com.sammy.miniecommerce.models.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private final List<Order> orderList;
    private final Context context;
    private OnItemClickListener mOnItemClickListener, onTrackListener;

    public OrdersAdapter(Context context, List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public void setOnTrackListener(OnItemClickListener onTrackListener) {
        this.onTrackListener = onTrackListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        OrderListItemBinding listBinding = OrderListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        Order order = orderList.get(position);
        holder.listItemBinding.setOrder(order);
        holder.listItemBinding.lytParent.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, order, position);
            }
        });
        holder.listItemBinding.btnTrack.setOnClickListener(view -> {
            if (onTrackListener != null) {
                onTrackListener.onItemClick(view, order, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Order order, int position);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private final OrderListItemBinding listItemBinding;

        public OrderViewHolder(OrderListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }


}
