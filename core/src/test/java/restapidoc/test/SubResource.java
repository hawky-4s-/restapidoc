package restapidoc.test;

import javax.ws.rs.Path;

@Path("subresource")
public class SubResource extends Resource {

  @Path("myStuff")
  public String myStuff() {
    return null;
  }

}
