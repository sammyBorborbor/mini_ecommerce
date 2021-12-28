package com.sammy.miniecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sammy.miniecommerce.databinding.ImageListItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class ImagesAdapter extends
        SliderViewAdapter<ImagesAdapter.ImageViewHolder> {

    private final Context context;
    private final List<String> mBanners;

    public ImagesAdapter(Context context, List<String> mBanners) {
        this.context = context;
        this.mBanners = mBanners;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageListItemBinding listItemBinding = ImageListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int position) {
        String imageUrl = mBanners.get(position);
        viewHolder.listItemBinding.setImageUrl(imageUrl);
        viewHolder.listItemBinding.getRoot().setOnClickListener(view -> {

        });

    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

    static class ImageViewHolder extends ViewHolder {

        private final ImageListItemBinding listItemBinding;

        public ImageViewHolder(ImageListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }
    }
}
