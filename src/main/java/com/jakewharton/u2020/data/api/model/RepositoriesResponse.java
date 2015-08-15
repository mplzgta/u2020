package com.jakewharton.u2020.data.api.model;

import java.util.List;

//encapsulation of github repo list
public final class RepositoriesResponse {
  public final List<Repository> items;

  public RepositoriesResponse(List<Repository> items) {
    this.items = items;
  }
}
