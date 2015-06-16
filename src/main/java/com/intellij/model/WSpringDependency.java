package com.intellij.model;

import org.jetbrains.annotations.NotNull;

/**
 * @author Ivan Chirkov
 */
public class WSpringDependency {
  @NotNull
  public final String modelName;

  @NotNull
  final LocalModelDependencyType dependencyType;

  public WSpringDependency(@NotNull String name, @NotNull LocalModelDependencyType type) {
    modelName = name;
    dependencyType = type;
  }

//  public WSpringDependency(@NotNull Pair<LocalModel, LocalModelDependency> model) {
//    dependencyType = model.second.getType();
//    modelName = model.first.getConfig().getContainingFile().getName();
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WSpringDependency that = (WSpringDependency)o;

    if (!modelName.equals(that.modelName)) return false;
    if (dependencyType != that.dependencyType) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = modelName.hashCode();
    result = 31 * result + dependencyType.hashCode();
    return result;
  }
}
