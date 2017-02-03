package com.ladwa.aditya.image.injection.component;

import dagger.Subcomponent;
import com.ladwa.aditya.image.injection.PerActivity;
import com.ladwa.aditya.image.injection.module.ActivityModule;
import com.ladwa.aditya.image.ui.base.BaseActivity;
import com.ladwa.aditya.image.ui.detail.DetailActivity;
import com.ladwa.aditya.image.ui.main.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
