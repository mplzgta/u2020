package com.jakewharton.u2020.data.api;

import com.squareup.moshi.Moshi;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import retrofit.MoshiConverterFactory;
import retrofit.ObservableCallAdapterFactory;
import retrofit.Retrofit;

@Module(
    complete = false,
    library = true
)
public final class ApiModule {
  public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com");

  @Provides @Singleton HttpUrl provideBaseUrl() {
    return PRODUCTION_API_URL;
  }

  @Provides @Singleton @Named("Api") OkHttpClient provideApiClient(OkHttpClient client) {
    return client.clone();
  }

  @Provides @Singleton
  Retrofit provideRetrofit(HttpUrl baseUrl, @Named("Api") OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder() //
        .client(client) //
        .baseUrl(baseUrl) //
        .converterFactory(MoshiConverterFactory.create(moshi)) //
        .callAdapterFactory(ObservableCallAdapterFactory.create()) //
        .build();
  }

  @Provides @Singleton GithubService provideGithubService(Retrofit retrofit) {
    return retrofit.create(GithubService.class);
  }
}
