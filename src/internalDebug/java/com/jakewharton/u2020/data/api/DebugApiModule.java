package com.jakewharton.u2020.data.api;

import android.content.SharedPreferences;
import com.jakewharton.u2020.data.ApiEndpoint;
import com.jakewharton.u2020.data.IsMockMode;
import com.jakewharton.u2020.data.prefs.StringPreference;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.android.AndroidMockValuePersistence;

@Module(
    //overrides = true        //Dagger 2 doesn't support overrides. Modules that override for simple testing fakes can
        //create a subclass of the module to emulate that behavior. Modules that use overrides and
        //rely on dependency injection should be decomposed so that the overriden modules are instead
        //represented as a choice between two modules.
)
public final class DebugApiModule extends ApiModule {

  @Provides @Singleton
  Endpoint provideEndpoint(@ApiEndpoint StringPreference apiEndpoint) {
    return Endpoints.newFixedEndpoint(apiEndpoint.get());
  }

  @Provides @Singleton @Named("Api")
  OkHttpClient provideApiClient(OkHttpClient client, LoggingInterceptor loggingInterceptor) {
    client = client.clone();
    client.interceptors().add(loggingInterceptor);
    return client;
  }


  //todo: why no provideRestAdapter here?
  //I think the solution may be that DataModule includes APIModule??? But there is DebugDataModule too...
  @Provides @Singleton
  MockRestAdapter provideMockRestAdapter(RestAdapter restAdapter, SharedPreferences preferences) {
    MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
    AndroidMockValuePersistence.install(mockRestAdapter, preferences);
    return mockRestAdapter;
  }

  //mock mode triggers creation of different github service, at time of object graph construction
  @Provides @Singleton
  GithubService provideGithubService(RestAdapter restAdapter, MockRestAdapter mockRestAdapter,
      @IsMockMode boolean isMockMode, MockGithubService mockService) {
    if (isMockMode) {
      return mockRestAdapter.create(GithubService.class, mockService);
    }
    return restAdapter.create(GithubService.class);
  }
}
