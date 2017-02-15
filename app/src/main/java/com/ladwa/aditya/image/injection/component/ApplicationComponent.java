package com.ladwa.aditya.image.injection.component;

import android.app.Application;
import android.content.Context;

import com.ladwa.aditya.image.data.DataManager;
import com.ladwa.aditya.image.data.local.PreferencesHelper;
import com.ladwa.aditya.image.data.remote.MvpStarterService;
import com.ladwa.aditya.image.injection.ApplicationContext;
import com.ladwa.aditya.image.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    PreferencesHelper preferencesHelper();

    MvpStarterService mvpBoilerplateService();
}
