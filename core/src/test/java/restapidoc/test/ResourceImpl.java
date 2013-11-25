package restapidoc.test;

import restapidoc.test.dto.ResourceDto;

import java.util.List;

public class ResourceImpl implements ResourceInterface {

  @Override
  public List<ResourceDto> getResources() {
    return null;
  }

  @Override
  public ResourceDto createResource(ResourceDto resourceDto) {
    return resourceDto;
  }

}
