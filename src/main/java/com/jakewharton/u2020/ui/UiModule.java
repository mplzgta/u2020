package com.jakewharton.u2020.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    injects = {
        MainActivity.class,
    },
    complete = false,
    library = true
)
public final class UiModule {
  @Provides @Singleton AppContainer provideAppContainer() {
    return AppContainer.DEFAULT;
  }

    //this is that silly hierarchy server thing that shows the view hierarchy on the dev machine
    //see SocketActivityHierarchyServer
  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }
}
