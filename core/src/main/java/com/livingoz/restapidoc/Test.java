//package com.livingoz.restapidoc;
//
//import org.jboss.resteasy.annotations.providers.jaxb.Formatted;
//import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
//import org.jboss.resteasy.core.Dispatcher;
//import org.jboss.resteasy.core.ResourceInvoker;
//import org.jboss.resteasy.core.ResourceMethod;
//import org.jboss.resteasy.core.ResourceMethodRegistry;
//import org.jboss.resteasy.mock.MockDispatcherFactory;
//import org.jboss.resteasy.mock.MockHttpRequest;
//import org.jboss.resteasy.mock.MockHttpResponse;
//import org.junit.Test;
//import sun.rmi.server.Dispatcher;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlValue;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class PrintAllResourcesTest {
//
//  @Test
//  public void name_StateUnderTest_ExpectedBehavior() throws Exception {
//    Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
//
//    dispatcher.getRegistry().addSingletonResource(new MetaService());
//    dispatcher.getRegistry().addSingletonResource(new Service());
//
//    MockHttpResponse response = new MockHttpResponse();
//    MockHttpRequest request = MockHttpRequest.get("/meta")
//        .accept(MediaType.APPLICATION_XML);
//
//
//    dispatcher.invoke(request, response);
//
//         /*<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
//         <resources>
//            <resource method="GET">/service/</resource>
//            <resource method="POST">/service/</resource>
//         </resources>*/
//    String result = response.getContentAsString();
//  }
//
//  @XmlRootElement(name = "resource")
//  public static final class JaxRsResource {
//    @XmlAttribute String method;
//    @XmlValue String uri;
//
//    public JaxRsResource() {}
//
//    public JaxRsResource(String method, String uri) {
//      this.method = method;
//      this.uri = uri;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
//
//      JaxRsResource that = (JaxRsResource) o;
//
//      if (method != null ? !method.equals(that.method) : that.method != null) return false;
//      if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;
//
//      return true;
//    }
//
//    @Override
//    public int hashCode() {
//      int result = method != null ? method.hashCode() : 0;
//      result = 31 * result + (uri != null ? uri.hashCode() : 0);
//      return result;
//    }
//  }
//
//  @Path("/service")
//  public static final class Service {
//
//    @GET
//    @Path("/")
//    public String getStuff(){
//      return "";
//    }
//
//
//    @POST
//    @Path("/")
//    public String postStuff(){
//      return "";
//    }
//  }
//
//
//  @Path("/meta")
//  public static final class MetaService {
//    @Context Dispatcher dispatcher;
//
//    @GET
//    @Path("/")
//    @Wrapped(element = "resources")
//    @Formatted
//    public Set<JaxRsResource> getAllResources(){
//      Set<JaxRsResource> resources = new HashSet<JaxRsResource>();
//
//      ResourceMethodRegistry registry = (ResourceMethodRegistry) dispatcher.getRegistry();
//
//      for (Map.Entry<String, List<ResourceInvoker>> entry : registry.getRoot().getBounded().entrySet()) {
//        for (ResourceInvoker invoker : entry.getValue()) {
//          ResourceMethod method = (ResourceMethod) invoker;
//
//          if(method.getMethod().getDeclaringClass() == getClass()){
//            continue;
//          }
//
//          for (String verb : method.getHttpMethods()) {
//            String uri = entry.getKey();
//            resources.add(new JaxRsResource(verb, uri));
//          }
//        }
//      }
//
//      return resources;
//    }
//
//  }
//}