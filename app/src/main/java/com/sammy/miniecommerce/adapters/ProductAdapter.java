package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sammy.miniecommerce.databinding.NewArrivalListItemBinding;
import com.sammy.miniecommerce.databinding.ProductListItemBinding;
import com.sammy.miniecommerce.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private final Context context;
    private OnItemClickListener mOnItemClickListener;

    public ProductAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProductListItemBinding listBinding = ProductListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

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

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ProductListItemBinding listItemBinding;

        public ProductViewHolder(ProductListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }


}
