package com.ladwa.aditya.image.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.ladwa.aditya.image.data.DataManager;
import com.ladwa.aditya.image.data.remote.MvpStarterService;
import com.ladwa.aditya.image.injection.ApplicationContext;
import com.ladwa.aditya.image.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    MvpStarterService mvpBoilerplateService();
}
