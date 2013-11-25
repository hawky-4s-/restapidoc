package restapidoc.test;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RestApplication extends javax.ws.rs.core.Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(Resource.class);
    classes.add(SubResource.class);

    return classes;
  }

  @Override
  public Set<Object> getSingletons() {
    Set<Object> singletons = new HashSet<Object>();

    return singletons;
  }
}
