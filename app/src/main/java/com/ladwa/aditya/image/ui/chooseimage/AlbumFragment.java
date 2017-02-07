package com.ladwa.aditya.image.ui.chooseimage;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.data.model.Bucket;
import com.ladwa.aditya.image.data.model.Photo;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by aditya on 3/2/17.
 */

public class AlbumFragment extends Fragment implements MediaLoader.Callbacks, AlbumBucketAdapter.AlbumClickListener {

    @BindView(R.id.album_recycler_view)
    RecyclerView albumRecyclerView;
    @BindView(R.id.textView)
    TextView textView;
    private MediaLoader mMediaLoader;
    public static final String IMAGE_TYPE_BMP = "image/bmp";
    public static final String IMAGE_TYPE_JPEG = "image/jpeg";
    public static final String IMAGE_TYPE_PNG = "image/png";
    public static final String[] IMAGE_TYPES = {IMAGE_TYPE_BMP, IMAGE_TYPE_JPEG, IMAGE_TYPE_PNG};
    private ArrayList<Bucket> bucketArrayList;
    private ArrayList<Photo> photoArrayList;
    private AlbumBucketAdapter albumBucketAdapter;
    private PhotoAdapter photoAdapter;

    public static Fragment newInstance() {
        return new AlbumFragment();
    }

    public AlbumFragment() {
        mMediaLoader = new MediaLoader();
        mMediaLoader.setMediaTypes(IMAGE_TYPES);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMediaLoader.onAttach((AppCompatActivity) context, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_album, container, false);


        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Timber.d("Permission Granted");
                        mMediaLoader.loadBuckets();
                    } else {
                        Timber.d("Please grant permission");
                        Toast.makeText(getActivity(), "We need external storage permission", Toast.LENGTH_SHORT).show();
                    }
                });
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMediaLoader.onDetach();
    }


    @Override
    public void onBucketLoadFinished(@Nullable Cursor data) {
        if (data != null) {
            bucketArrayList = new ArrayList<>();
            while (data.moveToNext()) {
                Bucket b = new Bucket();
                String albumName = data.getString(data.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME));
                Long id = data.getLong(data.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID));
                String dataStr = data.getString(data.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                b.setAlbumName(albumName);
                b.setTotalPhoto(photoCountByAlbum(albumName));
                b.setId(id);
                b.setThumbnailUri(dataStr);
                bucketArrayList.add(b);
                Timber.d(albumName);
                Timber.d(String.valueOf(photoCountByAlbum(albumName)));
                Timber.d(dataStr);
            }
            setUpAdapter(bucketArrayList);
        }
    }

    private void setUpAdapter(ArrayList<Bucket> bucketArrayList) {
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        albumBucketAdapter = new AlbumBucketAdapter(bucketArrayList, this);
        albumRecyclerView.setAdapter(albumBucketAdapter);
    }

    @Override
    public void onMediaLoadFinished(@Nullable Cursor data) {
        if (data != null) {
            photoArrayList = new ArrayList<>();
            while (data.moveToNext()) {
                Photo photo = new Photo();
                photo.setId(data.getLong(data.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID)));
                photo.setUri(data.getString(data.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
                photo.setDisplayName(data.getString(data.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)));

                photoArrayList.add(photo);
            }

            albumRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            photoAdapter = new PhotoAdapter(photoArrayList);
            albumRecyclerView.setAdapter(photoAdapter);
            Timber.d("Set the data");
        }
    }


    private int photoCountByAlbum(String bucketName) {
        try {
            final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
            String searchParams = null;
            searchParams = "bucket_display_name = \"" + bucketName + "\"";

            Cursor mPhotoCursor = getActivity().getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                    searchParams, null, orderBy + " DESC");

            if (mPhotoCursor.getCount() > 0) {
                return mPhotoCursor.getCount();
            }
            mPhotoCursor.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }


    @Override
    public void onClickBucket(Bucket bucket) {
        mMediaLoader.loadByBucket(bucket.getId());
    }
}
