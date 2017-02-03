package com.ladwa.aditya.image.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.ladwa.aditya.image.data.remote.MvpStarterService;
import com.ladwa.aditya.image.data.remote.MvpStarterServiceFactory;
import com.ladwa.aditya.image.injection.ApplicationContext;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    static MvpStarterService provideMvpBoilerplateService() {
        return MvpStarterServiceFactory.makeStarterService();
    }
}
