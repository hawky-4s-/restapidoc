package com.livingoz.restapidoc;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassPathScanner {

  public Set<Class<?>> scanPackageForClassesWithAnnotation(String packageName, Class<? extends Annotation> annotation) {
    Reflections reflections = new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage(packageName)).build();

    Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);

    return annotatedClasses;
  }

}
