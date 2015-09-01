package com.jakewharton.u2020.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.jakewharton.u2020.R;
import com.jakewharton.u2020.data.Injector;
//import dagger.ObjectGraph;

/*
 * This is the 'configure on start up' activity that doesn't show up in release
 */
public final class DebugActivity extends Activity {
//  private ObjectGraph appGraph;

  @Override protected void onCreate(Bundle savedInstanceState) {
    if (true) throw new RuntimeException();
    super.onCreate(savedInstanceState);
//    appGraph = Injector.obtain(getApplication());
    setContentView(R.layout.debug_activity);
  }

//  @Override public Object getSystemService(@NonNull String name) {
//    if (Injector.matchesService(name)) {
//      return appGraph;
//    }
//    return super.getSystemService(name);
//  }
}
