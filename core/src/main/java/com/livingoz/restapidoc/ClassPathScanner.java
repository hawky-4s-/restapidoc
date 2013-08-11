package com.livingoz.restapidoc;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class ClassPathScanner {

  private AbstractScanner[] scanners = {
      new TypeAnnotationsScanner(),
      new MethodAnnotationsScanner(),
      new MethodParameterScanner(),
      new FieldAnnotationsScanner(),
      new SubTypesScanner()
  };

  private Reflections reflections;

  public ClassPathScanner(String packageName) {
    reflections = new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage(packageName))
        .addScanners(scanners)
        .build();
  }

  public Set<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation) {
    Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);
    return annotatedClasses;
  }

  public Set<Method> getAnnotatedClassMethods(Class clazz, Class<? extends Annotation> annotation) {
    Set<Method> annotatedMethods = reflections.getStore().getMethodsAnnotatedWith(annotation);
    return annotatedMethods;
  }

  public Set<Field> getAnnotatedClassFields(Class clazz, Class<? extends Annotation> annotation) {
    Set<Field> annotatedFields = reflections.getFieldsAnnotatedWith(annotation);
    return annotatedFields;
  }

}
