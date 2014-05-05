package com.livingoz.jersey;

import com.livingoz.restapidoc.ClassPathScanner;
import com.livingoz.restapidoc.scan.Resources;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.model.RuntimeResourceModel;
import org.junit.Test;
import restapidoc.test.RestApplication;

import javax.ws.rs.core.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JerseyTest {

  @Test
  public void testJersey() {
    ApplicationHandler applicationHandler = new ApplicationHandler(RestApplication.class);

    ServiceLocator serviceLocator = applicationHandler.getServiceLocator();
    RuntimeResourceModel runtimeResourceModel = serviceLocator.getService(JerseyResourceContext.class).getResourceModel().getRuntimeResourceModel();

    assertEquals(3, runtimeResourceModel.getRuntimeResources().size());
  }

  @Test
  public void testJerseyApplicatio() {
    ClassPathScanner classPathScanner = new ClassPathScanner("org.camunda");
    Class<? extends Application> jaxRsApplicationSubclass = classPathScanner.getJaxRsApplicationSubclass();

    Set<Resource> resources = Resources.getAll(jaxRsApplicationSubclass, null);
    assertNotNull(resources);

    ArrayList<Resource> sortedResources = new ArrayList<>(resources);
    Collections.sort(sortedResources, new Comparator<Resource>() {
      @Override
      public int compare(Resource o1, Resource o2) {
        if (o1.getPath() != null && o2.getPath() != null) {
          int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getPath(), o2.getPath());
          return (res != 0) ? res : o1.getPath().compareTo(o2.getPath());
        }
        return 0;
      }
    });

    for (Resource resource : sortedResources) {
      printResource(resource);
    }
  }

  private void printResource(Resource resource) {
    StringBuilder sb = new StringBuilder();

    List<ResourceMethod> resourceMethods = resource.getResourceMethods();
    if (!resourceMethods.isEmpty()) {
      sb.append("Resource[");

      sb.append("\n\tpath=");
      Resource parent = resource.getParent();
      while (parent != null) {
        sb.append(parent.getPath());
        parent = parent.getParent();
      }

      sb.append(resource.getPath());
//      sb.append(",pattern=" + resource.getPathPattern());
//      sb.append(",name=" + resource.getName());
      sb.append(",\n\tmethods=[");

      for (ResourceMethod resourceMethod : resourceMethods) {
        sb.append("\n\t\t[" + resourceMethod.getHttpMethod());
        sb.append(",producedTypes=" + resourceMethod.getProducedTypes());

        sb.append(",nameBindings=" + resourceMethod.getNameBindings());

        Invocable invocable = resourceMethod.getInvocable();
        sb.append(",handlingMethod=" + invocable.getHandlingMethod().getName());

        if (invocable.getResponseType() != null) {
          sb.append(",responseType=" + invocable.getResponseType());
        } else {
          sb.append(",rawResponseType=" + invocable.getRawResponseType());
        }

        sb.append(",parameters[");
        for (Parameter parameter : invocable.getParameters()) {
          sb.append("[rawType=" + parameter.getRawType());
          sb.append(",type=" + parameter.getType() + "]");
        }
        sb.append("]");

        sb.append("]");
      }

      sb.append("\n\t]");
      System.out.println(sb.toString());
    }


    if (!resource.getChildResources().isEmpty()) {
      for (Resource childResource : resource.getChildResources()) {
        printResource(childResource);
      }
    }
  }

}
