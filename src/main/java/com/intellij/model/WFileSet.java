package com.intellij.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Ivan Chirkov
 */
public class WFileSet {
  @NotNull
  public final String name;

  @NotNull
  public final ArrayList<WModel> models = new ArrayList<WModel>();

  public WFileSet(@NotNull String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WFileSet set = (WFileSet)o;

    if (!name.equals(set.name)) return false;
    if (!models.equals(set.models)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + models.hashCode();
    return result;
  }
}
