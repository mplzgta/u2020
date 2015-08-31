package com.jakewharton.u2020;

import com.jakewharton.u2020.data.LumberYard;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpozega on 8/31/15.
 */
@Singleton
@Component(
        modules = {
                U2020Module.class
        }
)
public interface U2020Component {
    void inject(LumberYard lumberYard);

    LumberYard lumberYard();
}
