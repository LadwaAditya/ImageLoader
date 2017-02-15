package com.ladwa.aditya.image.ui.chooseimage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by aditya on 3/2/17.
 */

public class FacebookFragment extends BaseFragment {

    @BindView(R.id.btn_facebook)
    Button btnFacebook;

    public static Fragment newInstance() {
        return new FacebookFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_facebook_album;
    }


}