package com.sap.test;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class MainServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("https://oauthasservices-i076326trial.hanatrial.ondemand.com/oauth2/api/v1/token");

    MultivaluedMap<String, String> body = new MultivaluedStringMap();
    body.add("grant_type", "authorization_code");
    body.add("redirect_uri", "https://saphcpoauthcliep1941828300tria.hanatrial.ondemand.com/sap-hcp-oauth-client-1.0-SNAPSHOT");
    body.add("client_id", "9d8dbc3c-05fa-35dc-8241-ad18650f1755");

    String authorizationCode = req.getParameter("code");

    if (Objects.nonNull(authorizationCode)) {
      body.add("code", authorizationCode);
      Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.form(body));
      resp.getWriter().println(response.readEntity(String.class));
    }
    else
      resp.getWriter().println("Hello World");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BufferedReader reader = req.getReader();

    String line;
    StringBuilder response = new StringBuilder();

    while ((line = reader.readLine()) != null) {
      response = response.append(line);
    }
    System.out.println(response.toString());
    resp.getWriter().println("Hello World");
  }
}