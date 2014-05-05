package com.livingoz.restapidoc.scan;

import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Resources {

  public static Set<Resource> getAll(Class<? extends Application> applicationClass, String basePath) {
    Set<Resource> resources = new HashSet<Resource>();

    ApplicationHandler applicationHandler = new ApplicationHandler(applicationClass);
    ResourceConfig resourceConfig = applicationHandler.getConfiguration();

    for (Class<?> aClass : resourceConfig.getClasses()) {
      if (isAnnotatedResourceClass(aClass)) {
        Resource resource = Resource.builder(aClass).build();
        resources.add(resource);
      }
    }

    return resources;
  }

  private static boolean isAnnotatedResourceClass(Class rc) {
    if (rc.isAnnotationPresent(Path.class)) {
      return true;
    }

    for (Class i : rc.getInterfaces()) {
      if (i.isAnnotationPresent(Path.class)) {
        return true;
      }
    }

    return false;
  }
}
