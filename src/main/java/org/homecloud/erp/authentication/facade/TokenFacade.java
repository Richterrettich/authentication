package org.homecloud.erp.authentication.facade;

import org.homecloud.erp.authentication.entity.User;
import org.homecloud.erp.authentication.service.AuthenticationService;
import org.homecloud.erp.authentication.service.KeyService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rene on 20.02.16.
 */
@Stateless
public class TokenFacade {

  @EJB
  private AuthenticationService authenticationService;

  @EJB
  private KeyService keyService;


  @POST
  @Path("/token")
  public Map<String,String> getToken(String email, String passwort) {
    User user = authenticationService.authenticate(email,passwort);
    if (user == null) throw new NotFoundException();

    Map<String,String> result = new HashMap<>();
    result.put("access_token",keyService.createJWT(user));
    result.put("toke_type","bearer");
    return result;
  }

  @GET
  @Path("/public_key")
  public String  getKey(){
    return this.keyService.getVerificationKey();
  }
}
