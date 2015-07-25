package com.jakewharton.u2020.data;

import org.threeten.bp.Instant;

@SuppressWarnings("unused") // Accessed via reflection by Moshi.
public final class InstantAdapter {
  public String fromInstant(Instant instant) {
    return instant.toString();
  }

  public Instant fromInstant(String value) {
    return Instant.parse(value);
  }
}
