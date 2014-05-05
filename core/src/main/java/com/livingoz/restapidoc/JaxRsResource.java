package com.livingoz.restapidoc;

/**
 *
 */
public class JaxRsResource {

  private final String method;
  private final String uri;

  public JaxRsResource(String method, String uri) {
    this.method = method;
    this.uri = uri;
  }

}
