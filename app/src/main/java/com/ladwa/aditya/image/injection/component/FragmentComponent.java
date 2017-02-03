package com.ladwa.aditya.image.injection.component;

import dagger.Subcomponent;
import com.ladwa.aditya.image.injection.PerFragment;
import com.ladwa.aditya.image.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}