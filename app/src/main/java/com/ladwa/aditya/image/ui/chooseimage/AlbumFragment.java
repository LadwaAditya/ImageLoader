package com.ladwa.aditya.image.ui.chooseimage;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ladwa.aditya.image.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import timber.log.Timber;

/**
 * Created by aditya on 3/2/17.
 */

public class AlbumFragment extends Fragment implements MediaLoader.Callbacks {

    private MediaLoader mMediaLoader;
    public static final String IMAGE_TYPE_BMP = "image/bmp";
    public static final String IMAGE_TYPE_JPEG = "image/jpeg";
    public static final String IMAGE_TYPE_PNG = "image/png";
    public static final String[] IMAGE_TYPES = {IMAGE_TYPE_BMP, IMAGE_TYPE_JPEG, IMAGE_TYPE_PNG};

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
        rxPermissions.request(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Timber.d("Permission Granted");
                        mMediaLoader.loadBuckets();
                    } else {
                        Timber.d("Please grant permission");
                        Toast.makeText(getActivity(), "We need external storage permission", Toast.LENGTH_SHORT).show();
                    }
                });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMediaLoader.onDetach();
    }

    private void getAlbums() {
        final String BUCKET_SELECTION = "1) GROUP BY (1";
        final String BUCKET_SORT_ORDER = "MAX(" + MediaStore.Images.Media.DATE_TAKEN + ") DESC";
        final String[] BUCKET_PROJECTION = {
                MediaStore.Images.ImageColumns.BUCKET_ID,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA
        };

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = getActivity().getContentResolver().query(uri, BUCKET_PROJECTION,
                null,
                null,
                null
        );

        if (cursor != null) {
            Timber.d(String.valueOf(cursor.getCount()));
            while (cursor.moveToNext()) {
                Timber.d("Cursor");
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

    }

    @Override
    public void onBucketLoadFinished(@Nullable Cursor data) {
        if (data != null) {
            while (data.moveToNext()) {
                String albumName = data.getString(data.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME));

                Timber.d(albumName);
                Timber.d(String.valueOf(photoCountByAlbum(albumName)));
            }

        }
    }

    @Override
    public void onMediaLoadFinished(@Nullable Cursor data) {

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

}
