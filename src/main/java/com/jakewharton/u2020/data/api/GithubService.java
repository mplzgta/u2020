package com.jakewharton.u2020.data.api;

import com.jakewharton.u2020.data.api.model.RepositoriesResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

//typical retrofit http call service
public interface GithubService {
  @GET("/search/repositories") //
  Observable<RepositoriesResponse> repositories( //
      @Query("q") SearchQuery query, //
      @Query("sort") Sort sort, //
      @Query("order") Order order);
}
