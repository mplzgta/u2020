package com.jakewharton.u2020.ui;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class UiModule {
  @Provides @Singleton AppContainer provideAppContainer() {
    return getAppContainer(); //AppContainer.DEFAULT;
  }

    //this is that silly hierarchy server thing that shows the view hierarchy on the dev machine
    //see SocketActivityHierarchyServer
  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return getActivityHierarchyServer();
  }

  ActivityHierarchyServer getActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }

  AppContainer getAppContainer() { return AppContainer.DEFAULT; }
}
