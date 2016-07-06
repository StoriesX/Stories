package com.stories.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Testing {

  public static void main(String[] args) {
    ClientConfig config = new ClientConfig();

    Client client = ClientBuilder.newClient(config);

    WebTarget target = client.target(getBaseURI());
    //String response = target.path("v1").path("register").request().accept(MediaType.TEXT_PLAIN).get().toString();

    //String plainAnswer = target.path("v1").path("register").request().accept(MediaType.TEXT_PLAIN).get(String.class);
    //String xmlAnswer = target.path("v1").path("register").request().accept(MediaType.TEXT_XML).get(String.class);
    //String htmlAnswer= target.path("v1").path("register").request().accept(MediaType.TEXT_HTML).get(String.class);
    Form form =new Form();
    form.param("username", "user");
    form.param("password", "abcd");
    Response response = target.path("v1").path("register").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED), Response.class);
    System.out.println("Form response " + response.getStatus());
    //System.out.println(response);
    //System.out.println(plainAnswer);
    //System.out.println(xmlAnswer);
    //System.out.println(htmlAnswer);
  }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8080/com.stories/rest").build();
  }
}