package com.intellij.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Ivan Chirkov
 */
public class SpringWFacet implements WFacet {
  @NotNull
  public final ArrayList<WFileSet> filesets = new ArrayList<WFileSet>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SpringWFacet facet = (SpringWFacet)o;

    if (!filesets.equals(facet.filesets)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return filesets.hashCode();
  }
}
