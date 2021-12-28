package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sammy.miniecommerce.databinding.CategoryListItemBinding;
import com.sammy.miniecommerce.models.Product;
import com.sammy.miniecommerce.models.ProductCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<ProductCategory> categoryList;
    private final Context context;
    private OnItemClickListener mOnItemClickListener;

    public CategoryAdapter(Context context, List<ProductCategory> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategoryListItemBinding listBinding = CategoryListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        ProductCategory category = categoryList.get(position);
        holder.listItemBinding.setCategory(category);
        holder.listItemBinding.lytParent.setOnClickListener(view -> {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, category, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
        void onItemClick(View view, ProductCategory productCategory, int position);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final CategoryListItemBinding listItemBinding;

        public CategoryViewHolder(CategoryListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }

    }


}
