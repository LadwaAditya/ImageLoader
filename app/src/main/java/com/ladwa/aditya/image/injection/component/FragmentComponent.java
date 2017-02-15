package com.ladwa.aditya.image.injection.component;

import com.ladwa.aditya.image.injection.PerFragment;
import com.ladwa.aditya.image.injection.module.FragmentModule;
import com.ladwa.aditya.image.ui.chooseimage.InstagramFragment;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(InstagramFragment instagramFragment);
}