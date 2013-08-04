package com.livingoz.restapidoc;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ClassPathScannerTest {

  @Test
  public void shouldFindApplicationPathAnnotatedClassInPackage() {
    ClassPathScanner scanner = new ClassPathScanner();
    Set<Class<?>> classes = scanner.scanPackageFor("org.camunda.bpm.engine.rest");

    assertEquals(1, classes.size());
  }

}
