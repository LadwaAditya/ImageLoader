package com.ladwa.aditya.image.ui.chooseimage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.data.model.Photo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 07-Feb-17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private ArrayList<Photo> photoArrayList;

    public PhotoAdapter(ArrayList<Photo> photoArrayList) {
        this.photoArrayList = photoArrayList;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.setPhoto(photoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return photoArrayList.size() != 0 ? photoArrayList.size() : 0;
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setPhoto(Photo photo) {
            Glide.with(this.itemView.getContext())
                    .load(photo.getUri())
                    .centerCrop()
                    .placeholder(R.color.accent)
                    .into(this.imgPhoto);
        }
    }
}
