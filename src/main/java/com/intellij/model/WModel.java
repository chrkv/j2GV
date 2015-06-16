package com.intellij.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Ivan Chirkov
 */
public class WModel {
  @NotNull
  public final String name;

  @NotNull
  public final ModelType type;

  @NotNull
  public final ArrayList<WSpringDependency> dependencies = new ArrayList<WSpringDependency>();

  public WModel(@NotNull String name, @NotNull ModelType type) {
    this.name = name;
    this.type = type;
  }

//  public WModel(@NotNull LocalModel model) {
//    if (model instanceof LocalXmlModel) {
//      this.name = ((LocalXmlModel)model).getConfig().getVirtualFile().getPath();
//      this.type = ModelType.XML;
//    } else if (model instanceof LocalAnnotationModel) {
//      this.name = ((LocalAnnotationModel)model).getConfig().getQualifiedName();
//      this.type = ModelType.ANNO;
//    }
//    else throw new IllegalArgumentException(String.format("Can't get model type for model with class '%s'", model.getClass()));
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    WModel model = (WModel)o;

    if (!name.equals(model.name)) return false;
    if (type != model.type) return false;
    if (!dependencies.equals(model.dependencies)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + type.hashCode();
    result = 31 * result + dependencies.hashCode();
    return result;
  }
}
