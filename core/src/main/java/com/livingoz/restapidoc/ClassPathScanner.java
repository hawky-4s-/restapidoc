package com.livingoz.restapidoc;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

public class ClassPathScanner {

  public Set<Class<?>> scanPackageFor(String packageName) {
    Reflections reflections = new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forPackage(packageName)).build();

    Set<Class<? extends Application>> application = reflections.getSubTypesOf(Application.class);
    Set<Class<?>> applicationPath = reflections.getTypesAnnotatedWith(ApplicationPath.class);

    return applicationPath;
  }

}
