package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sammy.miniecommerce.databinding.CheckoutListItemBinding;
import com.sammy.miniecommerce.databinding.CheckoutListItemBinding;
import com.sammy.miniecommerce.models.Product;

import java.util.List;

public class CheckoutItemsAdapter extends RecyclerView.Adapter<CheckoutItemsAdapter.CheckoutViewHolder> {

    private final List<Product> productList;
    private final Context context;
    private OnItemClickListener mOnItemClickListener;

    public CheckoutItemsAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CheckoutListItemBinding listBinding = CheckoutListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CheckoutViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.listItemBinding.setProduct(product);
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

    public static class CheckoutViewHolder extends RecyclerView.ViewHolder {
        private final CheckoutListItemBinding listItemBinding;

        public CheckoutViewHolder(CheckoutListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }


}
