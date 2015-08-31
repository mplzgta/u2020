package com.jakewharton.u2020;

import android.app.Application;
import android.support.annotation.NonNull;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.jakewharton.u2020.data.DaggerDataComponent;
import com.jakewharton.u2020.data.DataComponent;
import com.jakewharton.u2020.data.DataModule;
import com.jakewharton.u2020.data.Injector;
import com.jakewharton.u2020.data.LumberYard;
import com.jakewharton.u2020.ui.ActivityHierarchyServer;
import com.jakewharton.u2020.ui.DaggerUiComponent;
import com.jakewharton.u2020.ui.MainActivityModule;
import com.jakewharton.u2020.ui.UiComponent;
import com.squareup.leakcanary.LeakCanary;
//import dagger.ObjectGraph;
import javax.inject.Inject;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class U2020App extends Application {
  //private ObjectGraph objectGraph;

  //to prevent gc, not sure how this is done in relaity
  private U2020Component u2020Component;
  public UiComponent uiComponent;
  public static DataComponent dataComponent;

  @Inject ActivityHierarchyServer activityHierarchyServer;
  @Inject LumberYard lumberYard;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    LeakCanary.install(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      // TODO Crashlytics.start(this);
      // TODO Timber.plant(new CrashlyticsTree());
    }

    u2020Component = DaggerU2020Component.builder()
            .u2020Module(new U2020Module(this))
            .dataModule(new DataModule(this))
            .build();
    uiComponent = DaggerUiComponent.builder().build();
    //objectGraph = ObjectGraph.create(Modules.list(this));
    //objectGraph.inject(this);

    lumberYard = u2020Component.lumberYard();
    lumberYard.setApp(this); //todo this can probably be injected into lumberyard

    lumberYard.cleanUp();
    Timber.plant(lumberYard.tree());

    activityHierarchyServer = uiComponent.activityHierarchyServer();
    registerActivityLifecycleCallbacks(activityHierarchyServer);
  }

//  @Override public Object getSystemService(@NonNull String name) {
//    if (Injector.matchesService(name)) {
//      return objectGraph;
//    }
//    return super.getSystemService(name);
//  }
}
