package org.rapidpm.microkernel.undertow;

import static java.lang.Integer.parseInt;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rapidpm.microkernel.api.Service;
import org.rapidpm.microkernel.legacy.ServiceLegacyImpl;

/**
 *
 */
@Path("/api")
public class ServiceREST {


  private Service service = new ServiceLegacyImpl();


  @GET
  @Path("add")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public Response add(@Context HttpServletRequest requestContext ,
                      @QueryParam("inputA") String inputA ,
                      @QueryParam("inputB") String inputB) {


    return Response
        .ok()
        .entity(service
                    .add(parseInt(inputA) ,
                         parseInt(inputB)))
        .build();
  }
}