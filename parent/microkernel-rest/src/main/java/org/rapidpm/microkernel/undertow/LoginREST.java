package org.rapidpm.microkernel.undertow;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rapidpm.microkernel.api.login.LoginService;
import org.rapidpm.microkernel.legacy.LoginServiceLegacyImpl;

/**
 *
 */
@Path("/security")
public class LoginREST {
  private LoginService service = new LoginServiceLegacyImpl();


  @GET
  @Path("check")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public Response add(@Context HttpServletRequest requestContext ,
                      @QueryParam("username") String inputA ,
                      @QueryParam("password") String inputB) {


    return Response
        .ok()
        .entity(service.check(inputA, inputB))
        .build();
  }
}
