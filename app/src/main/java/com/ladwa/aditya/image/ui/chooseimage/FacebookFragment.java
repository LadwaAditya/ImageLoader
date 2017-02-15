package com.ladwa.aditya.image.ui.chooseimage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ladwa.aditya.image.R;
import com.ladwa.aditya.image.data.local.PreferencesHelper;
import com.ladwa.aditya.image.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by aditya on 3/2/17.
 */

public class FacebookFragment extends BaseFragment implements FacebookCallback<LoginResult> {


    @Inject
    PreferencesHelper preferencesHelper;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    public static Fragment newInstance() {
        return new FacebookFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent().inject(this);
        loginButton.setReadPermissions("email","user_photos");
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_facebook_album;
    }


    @Override
    public void onSuccess(LoginResult loginResult) {
        Timber.d(loginResult.getAccessToken().toString());


    }


    @Override
    public void onCancel() {
        Timber.d("Login canceled");
    }

    @Override
    public void onError(FacebookException error) {
        error.printStackTrace();
    }
}