package com.jakewharton.u2020.data.api;

import com.jakewharton.u2020.data.ApiEndpoint;
import com.jakewharton.u2020.data.IsMockMode;
import com.jakewharton.u2020.data.prefs.StringPreference;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit.Retrofit;
import retrofit.mock.Behavior;
import retrofit.mock.MockRetrofit;
import retrofit.mock.ObservableBehaviorAdapter;

@Module(
    complete = false,
    library = true,
    overrides = true
)
public final class DebugApiModule {
  @Provides @Singleton HttpUrl provideHttpUrl(@ApiEndpoint StringPreference apiEndpoint) {
    return HttpUrl.parse(apiEndpoint.get());
  }

  @Provides @Singleton @Named("Api")
  OkHttpClient provideApiClient(OkHttpClient client, LoggingInterceptor loggingInterceptor) {
    client = client.clone();
    client.interceptors().add(loggingInterceptor);
    return client;
  }

  @Provides @Singleton Behavior provideBehavior() {
    return Behavior.create();
  }

  @Provides @Singleton MockRetrofit provideMockRetrofit(Behavior behavior) {
    // TODO figure out how to persist behavior preferences.
    return new MockRetrofit(ObservableBehaviorAdapter.create(), behavior);
  }

  @Provides @Singleton GithubService provideGithubService(Retrofit retrofit,
      MockRetrofit mockRetrofit, @IsMockMode boolean isMockMode, MockGithubService mockService) {
    if (isMockMode) {
      return mockRetrofit.create(GithubService.class, mockService);
    }
    return retrofit.create(GithubService.class);
  }
}
