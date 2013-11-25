package com.livingoz.restapidoc;

import org.junit.Test;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.util.Set;

import static org.junit.Assert.*;

public class ClassPathScannerTest {

  public static final String PACKAGE_ORG_CAMUNDA_BPM_ENGINE_REST = "org.camunda.bpm.engine.rest";
  public static final String PACKAGE_RESTAPIDOC_TEST = "restapidoc.test";

  @Test
  public void shouldFindPathAnnotatedClassesInPackage() {
    ClassPathScanner scanner = new ClassPathScanner(PACKAGE_ORG_CAMUNDA_BPM_ENGINE_REST);
    Set<Class<?>> classes = scanner.getClassesWithAnnotation(Path.class);

    assertEquals(16, classes.size());

    for (Class<?> clazz : classes) {
      Path annotation = clazz.getAnnotation(Path.class);
      System.out.println(clazz + " path: " + annotation.value());
    }
  }

  @Test
  public void shouldFindApplicationPathAnnotatedClassInPackage() {
    ClassPathScanner scanner = new ClassPathScanner(PACKAGE_RESTAPIDOC_TEST);
    Set<Class<?>> classes = scanner.getClassesWithAnnotation(ApplicationPath.class);

    assertEquals(1, classes.size());

    for (Class<?> clazz : classes) {
      ApplicationPath annotation = clazz.getAnnotation(ApplicationPath.class);
      System.out.println(clazz + " path: " + annotation.value());
    }
  }

  @Test
  public void getCamundaRestJaxRsApplicationSubclass() {
    ClassPathScanner scanner = new ClassPathScanner("org.camunda");
    Class<? extends javax.ws.rs.core.Application> jaxRsApplicationSubclass = scanner.getJaxRsApplicationSubclass();
    assertNotNull(jaxRsApplicationSubclass);
  }

}
