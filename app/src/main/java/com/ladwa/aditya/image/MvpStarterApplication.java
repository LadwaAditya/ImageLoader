package com.ladwa.aditya.image;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import com.ladwa.aditya.image.injection.component.ApplicationComponent;
import com.ladwa.aditya.image.injection.component.DaggerApplicationComponent;
import com.ladwa.aditya.image.injection.module.ApplicationModule;
import timber.log.Timber;

public class MvpStarterApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
