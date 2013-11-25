package restapidoc.test;

import restapidoc.test.dto.ResourceDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/resourceInterface")
public interface ResourceInterface {

  @Path("/resource")
  @GET
  public List<ResourceDto> getResources();

  @Path("/resource/create")
  @POST
  public ResourceDto createResource(ResourceDto resourceDto);

}
