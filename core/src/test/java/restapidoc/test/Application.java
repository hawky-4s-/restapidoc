package restapidoc.test;

import javax.ws.rs.ApplicationPath;
import java.util.Set;

@ApplicationPath("rest")
public class Application extends javax.ws.rs.core.Application {

  @Override
  public Set<Class<?>> getClasses() {
    return super.getClasses();    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Set<Object> getSingletons() {
    return super.getSingletons();    //To change body of overridden methods use File | Settings | File Templates.
  }
}
