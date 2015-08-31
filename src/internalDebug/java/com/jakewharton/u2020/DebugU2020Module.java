package com.jakewharton.u2020;

import com.jakewharton.u2020.data.DebugDataModule;
import com.jakewharton.u2020.ui.DebugUiModule;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    //addsTo = U2020Module.class,
    includes = {
        DebugUiModule.class,
        DebugDataModule.class
    }//Overrides not supported in Dagger 2
        //Dagger 2 doesn't support overrides. Modules that override for simple testing fakes can
        //create a subclass of the module to emulate that behavior. Modules that use overrides and
        //rely on dependency injection should be decomposed so that the overriden modules are instead
        //represented as a choice between two modules.
)
public final class DebugU2020Module extends U2020Module {
  // Low-tech flag to force certain debug build behaviors when running in an instrumentation test.
  // This value is used in the creation of singletons so it must be set before the graph is created.
  static boolean instrumentationTest = false;

    public DebugU2020Module(U2020App app) {
        super(app);
    }

    @Provides @Singleton @IsInstrumentationTest boolean provideIsInstrumentationTest() {
    return instrumentationTest;
  }
}
