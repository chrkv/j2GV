package com.intellij.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Ivan Chirkov
 */
public class WModule {
  @NotNull
  public final String name;

  @NotNull
  public final ArrayList<SpringWFacet> facets = new ArrayList<SpringWFacet>();

  public WModule(@NotNull String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WModule module = (WModule)o;

    if (!name.equals(module.name)) return false;
    if (!facets.equals(module.facets)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + facets.hashCode();
    return result;
  }
}
