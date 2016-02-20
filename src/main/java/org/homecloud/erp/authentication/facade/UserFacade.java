package org.homecloud.erp.authentication.facade;

import org.homecloud.erp.authentication.entity.User;
import org.homecloud.erp.authentication.service.AuthenticationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by rene on 20.02.16.
 */
@Stateless
public class UserFacade {

  @EJB
  private AuthenticationService authenticationService;

  @Context
  UriInfo uriInfo;

  @POST
  public Response registerUser(User user){
    String id = authenticationService.register(user);
    return Response.created(uriInfo.getAbsolutePathBuilder().path(id).build()).build();
  }

  @DELETE
  @Path("{id}")
  public void deleteUser(@PathParam("id") String id) {
    this.authenticationService.delete(id);
  }

}
