/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demos.boundary;

import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author chaks
 */
@Path("/")
@RequestScoped
public class JwtProtectedResource {

  @Inject
  private JsonWebToken jwtPrincipal;

  @Inject
  @Claim(standard = Claims.birthdate)
  Optional<String> birthdate;

  @GET
  @Path("/secured")
  @RolesAllowed({"DevUser", "QaUser"})
  @Produces(MediaType.TEXT_PLAIN)
  public String secured() {
    return birthdate.isPresent() ? birthdate.get() : null;
  }
}
