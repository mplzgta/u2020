package com.jakewharton.u2020.ui;

import com.jakewharton.u2020.IsInstrumentationTest;
import com.jakewharton.u2020.ui.debug.DebugAppContainer;
import com.jakewharton.u2020.ui.debug.DebugView;
import com.jakewharton.u2020.ui.debug.SocketActivityHierarchyServer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
//    injects = {
//        DebugAppContainer.class,
//        DebugView.class,
//    }
//    complete = false,
//    library = true,
    //overrides = true
        //Dagger 2 doesn't support overrides. Modules that override for simple testing fakes can
        //create a subclass of the module to emulate that behavior. Modules that use overrides and
        //rely on dependency injection should be decomposed so that the overriden modules are instead
        //represented as a choice between two modules.
)
public class DebugUiModule extends UiModule {
  @Provides @Singleton AppContainer provideAppContainer(DebugAppContainer debugAppContainer,
      @IsInstrumentationTest boolean isInstrumentationTest) {
    // Do not add the debug controls for when we are running inside of an instrumentation test.
    return isInstrumentationTest ? AppContainer.DEFAULT : debugAppContainer;
  }

  ActivityHierarchyServer getActivityHierarchyServer() {
    return new SocketActivityHierarchyServer();
  }
}
