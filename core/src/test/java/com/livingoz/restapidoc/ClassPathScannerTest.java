package com.livingoz.restapidoc;

import org.junit.Test;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ClassPathScannerTest {

  public static final String PACKAGE_ORG_CAMUNDA_BPM_ENGINE_REST = "org.camunda.bpm.engine.rest";

  @Test
  public void shouldFindApplicationPathAnnotatedClassInPackage() {
    ClassPathScanner scanner = new ClassPathScanner();
    Set<Class<?>> classes = scanner.scanPackageForClassesWithAnnotation(PACKAGE_ORG_CAMUNDA_BPM_ENGINE_REST, Path.class);

    assertEquals(11, classes.size());

    for (Class<?> klass : classes) {
      Path annotation = klass.getAnnotation(Path.class);
      System.out.println(klass + " path: " + annotation.value());
    }
  }

  @Test
  public void test() {
    Reflections reflections = new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage(PACKAGE_ORG_CAMUNDA_BPM_ENGINE_REST))
        .addScanners(new TypeElementsScanner()).build();
  }

}
