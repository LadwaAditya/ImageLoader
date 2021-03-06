package com.ladwa.aditya.image.ui.chooseimage;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.data.model.Bucket;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aditya on 04-Feb-17.
 */

public class AlbumBucketAdapter extends RecyclerView.Adapter<AlbumBucketAdapter.AlbumViewHolder> {


    private ArrayList<Bucket> bucketArrayList;
    private AlbumClickListener albumClickListener;

    public AlbumBucketAdapter(ArrayList<Bucket> bucketArrayList, AlbumClickListener albumClickListener) {
        this.bucketArrayList = bucketArrayList;
        this.albumClickListener = albumClickListener;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_album_bucket, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.setBucket(bucketArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return bucketArrayList.size() != 0 ? bucketArrayList.size() : 0;
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_thumbnail)
        ImageView imgThumbnail;
        @BindView(R.id.txt_album_name)
        AppCompatTextView txtAlbumName;
        @BindView(R.id.txt_total_photo)
        AppCompatTextView txtTotalPhoto;

        private void setBucket(Bucket bucket) {
            this.txtAlbumName.setText(bucket.getAlbumName());
            this.txtTotalPhoto.setText(String.format(this.itemView.getContext().getString(R.string.total_photo_format), bucket.getTotalPhoto()));
            Glide.with(this.itemView.getContext())
                    .load(bucket.getThumbnailUri())
                    .centerCrop()
                    .placeholder(R.color.accent)
                    .into(this.imgThumbnail);
        }

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION)
                albumClickListener.onClickBucket(bucketArrayList.get(getAdapterPosition()));
        }
    }

    interface AlbumClickListener {
        void onClickBucket(Bucket bucket);
    }
}
