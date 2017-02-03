package com.ladwa.aditya.image.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.ladwa.aditya.image.common.injection.module.ApplicationTestModule;
import com.ladwa.aditya.image.injection.component.ApplicationComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}