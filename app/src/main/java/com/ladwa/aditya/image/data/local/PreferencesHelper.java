package com.ladwa.aditya.image.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.ladwa.aditya.image.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "mvpstarter_pref_file";

    private final SharedPreferences mPref;


    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public String getInstagramToken() {
        return mPref.getString(Keys.INSTAGRAM_TOKEN, null);
    }

    public void setInstagramToken(String instagramToken) {
        mPref.edit().putString(Keys.INSTAGRAM_TOKEN, instagramToken).apply();
    }

    public String getFbtoken() {
        return mPref.getString(Keys.FACEBOOK_TOKEN, null);

    }

    public void setFbtoken(String fbtoken) {
        mPref.edit().putString(Keys.FACEBOOK_TOKEN, fbtoken).apply();

    }

    public String getFbuserid() {
        return mPref.getString(Keys.FACEBOOK_USER_ID, null);

    }

    public void setFbuserid(String fbuserid) {
        mPref.edit().putString(Keys.FACEBOOK_USER_ID, fbuserid).apply();
    }

    public interface Keys {
        String INSTAGRAM_TOKEN = "instatoken";
        String FACEBOOK_TOKEN = "fbtoken";
        String FACEBOOK_USER_ID = "fbuserid";
    }
}
