package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sammy.miniecommerce.databinding.NewArrivalListItemBinding;
import com.sammy.miniecommerce.models.Product;

import java.util.List;

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder> {

    private final List<Product> productList;
    private final Context context;
    private OnItemClickListener mOnItemClickListener;

    public NewArrivalAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public NewArrivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NewArrivalListItemBinding listBinding = NewArrivalListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new NewArrivalViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.listItemBinding.setProduct(product);
        holder.listItemBinding.lytParent.setOnClickListener(view -> {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, product, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
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
        void onItemClick(View view, Product product, int position);
    }

    public static class NewArrivalViewHolder extends RecyclerView.ViewHolder {
        private final NewArrivalListItemBinding listItemBinding;

        public NewArrivalViewHolder(NewArrivalListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }


}
