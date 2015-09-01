package com.jakewharton.u2020.data;

import android.app.Application;
import android.support.v4.widget.DrawerLayout;

import com.jakewharton.u2020.data.api.ApiModule;
import com.jakewharton.u2020.data.api.GithubService;
import com.jakewharton.u2020.ui.ActivityHierarchyServer;
import com.jakewharton.u2020.ui.MainActivity;
import com.jakewharton.u2020.ui.MainActivityModule;
import com.jakewharton.u2020.ui.UiModule;
import com.jakewharton.u2020.ui.trending.TrendingView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dpozega on 8/31/15.
 */
@Singleton
@Component(
        modules = {
                DataModule.class,
                MainActivityModule.class
        }
)
public interface DataComponent {
        void inject(TrendingView trendingView);
}
