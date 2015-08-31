package com.jakewharton.u2020.ui;

import com.jakewharton.u2020.U2020Module;
import com.jakewharton.u2020.data.LumberYard;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpozega on 8/31/15.
 */
@Singleton
@Component(
        modules = {
                UiModule.class
        }
)
public interface UiComponent {
    ActivityHierarchyServer activityHierarchyServer();

    void inject(MainActivity mainActivity);
}
