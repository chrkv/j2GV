package com.intellij.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Ivan Chirkov
 */
public class WProject {
  @NotNull
  public final ArrayList<WModule> modules = new ArrayList<WModule>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WProject project = (WProject)o;

    if (!modules.equals(project.modules)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return modules.hashCode();
  }
}
