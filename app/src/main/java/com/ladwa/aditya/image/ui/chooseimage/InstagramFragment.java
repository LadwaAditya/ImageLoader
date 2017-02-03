package com.ladwa.aditya.image.ui.chooseimage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ladwa.aditya.image.R;

/**
 * Created by aditya on 3/2/17.
 */

public class InstagramFragment extends Fragment {

    public static Fragment newInstance() {
        return new InstagramFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_instagram_album, container, false);
        return rootView;
    }
}