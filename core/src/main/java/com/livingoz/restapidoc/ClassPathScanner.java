package com.livingoz.restapidoc;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.ws.rs.core.Application;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

public class ClassPathScanner {

  private final String packageName;
  private Reflections reflections;

  public ClassPathScanner(String packageName) {
    this.packageName = packageName;
    init(packageName);
  }

  private void init(String packageName) {
    FilterBuilder filters = new FilterBuilder().includePackage(packageName);

    Scanner[] scanners = {
        new TypeAnnotationsScanner(),
        new MethodAnnotationsScanner(),
        new MethodParameterScanner(),
        new FieldAnnotationsScanner(),
        new SubTypesScanner().filterResultsBy(filters)
    };

    reflections = new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage(packageName))
        .addScanners(scanners)
        .filterInputsBy(filters)
        .build();
  }

  public Set<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation) {
    Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);
    return annotatedClasses;
  }

  public Set<Method> getAnnotatedClassMethods(Class clazz, Class<? extends Annotation> annotation) {
    Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(annotation);
    return annotatedMethods;
  }

  public Set<Field> getAnnotatedClassFields(Class clazz, Class<? extends Annotation> annotation) {
    Set<Field> annotatedFields = reflections.getFieldsAnnotatedWith(annotation);
    return annotatedFields;
  }

  public Class<? extends Application> getJaxRsApplicationSubclass() {
    Set<Class<? extends Application>> subTypesOf = reflections.getSubTypesOf(Application.class);
    if (subTypesOf.size() > 1) {
      throw new RuntimeException("Found too many classes extending " + Application.class.getName());
    } else if (subTypesOf.isEmpty()) {
      throw new RuntimeException("Found no classes extending " + Application.class.getName());
    } else {
      Class applicationClass = null;
      for (Iterator<Class<? extends Application>> iterator = subTypesOf.iterator(); iterator.hasNext(); ) {
        Class<? extends Application> aClass = iterator.next();
        applicationClass = aClass;
      }
      return applicationClass;
    }
  }

}
