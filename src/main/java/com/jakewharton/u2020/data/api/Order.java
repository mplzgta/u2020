package com.jakewharton.u2020.data.api;

//enumeration representing an ordering (ascending or descending)
public enum Order {
  ASC("asc"),
  DESC("desc");

  private final String value;

  Order(String value) {
    this.value = value;
  }

  @Override public String toString() {
    return value;
  }
}
