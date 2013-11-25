package restapidoc.test;

import javax.ws.rs.Path;

@Path("resource")
public class Resource {

  @Path("myStuff")
  public String myStuff() {
    return null;
  }

}
