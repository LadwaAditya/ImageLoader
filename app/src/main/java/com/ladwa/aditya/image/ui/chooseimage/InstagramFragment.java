package com.ladwa.aditya.image.ui.chooseimage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.data.local.PreferencesHelper;
import com.ladwa.aditya.image.ui.base.BaseFragment;
import com.ladwa.aditya.image.ui.chooseimage.instagramSignIn.InstagramHelper;
import com.ladwa.aditya.image.ui.chooseimage.instagramSignIn.InstagramResponse;
import com.ladwa.aditya.image.ui.chooseimage.instagramSignIn.InstagramUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by aditya on 3/2/17.
 */

public class InstagramFragment extends BaseFragment implements InstagramResponse {


    private static final String CLIENT_ID = "579c00d7c2fb4d88bf4e3ccf950a7344";
    private static final String CLIENT_SECREAT = "12ea92bd00a949ee80f0337cc077167e";
    private static final String CALLBACK_URL = "http://www.fuzzie.com.sg/";
    @BindView(R.id.btn_instagram_signin)
    Button btnInstagramSignin;
    private InstagramHelper mInstagramHelper;
    private String instaToken = null;

    @Inject
    PreferencesHelper preferencesHelper;

    public static Fragment newInstance() {
        return new InstagramFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        mInstagramHelper = new InstagramHelper(CLIENT_ID, CLIENT_SECREAT, CALLBACK_URL, getActivity(), this);
        instaToken = preferencesHelper.getInstagramToken();
        if (instaToken != null)
            btnInstagramSignin.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_instagram_album;
    }

    @OnClick(R.id.btn_instagram_signin)
    public void loginInstagram(View button) {
        if (instaToken == null)
            mInstagramHelper.performSignIn();
        else
            Toast.makeText(getActivity(), preferencesHelper.getInstagramToken(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInstagramSignInSuccess(InstagramUser user) {
        preferencesHelper.setInstagramToken(user.getAccesstoken());
    }

    @Override
    public void onInstagramSignInFail(String error) {
        Timber.d(error);
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}