package org.rapidpm.microkernel.undertow;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import io.undertow.Undertow;
import io.undertow.Undertow.Builder;
import io.undertow.UndertowOptions;

/**
 *
 */
public class MainRest {

  public static final String DEFAULT_HOST = "0.0.0.0";
  public static final String CONTEXT_PATH_REST = "/rest";
  public static final int DEFAULT_REST_PORT = 7081;
  public static final String REST_PORT_PROPERTY = "org.rapidpm.microservice.rest.port";
  public static final String REST_HOST_PROPERTY = "org.rapidpm.microservice.rest.host";
  private static final String RESTEASY_PORT_PROPERTY = "org.jboss.resteasy.port";
  private static final String RESTEASY_HOST_PROPERTY = "org.jboss.resteasy.host";


  private static UndertowJaxrsServer jaxrsServer;

  public static void main(String[] args) {
    final Builder builder = Undertow
        .builder()
        .setDirectBuffers(true)
        .setServerOption(UndertowOptions.ENABLE_HTTP2 , true);

    final Application jaxRsActivator = new Application() {
      @Override
      public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ServiceREST.class);
        classes.add(LoginREST.class);
        return classes;
      }
    };

    final String realRestPort = System.getProperty(REST_PORT_PROPERTY , DEFAULT_REST_PORT + "");
    final String realRestHost = System.getProperty(REST_HOST_PROPERTY , MainRest.DEFAULT_HOST);

    System.setProperty(RESTEASY_PORT_PROPERTY , realRestPort);
    System.setProperty(RESTEASY_HOST_PROPERTY , realRestHost);

    builder.addHttpListener(Integer.parseInt(realRestPort) , realRestHost);
    jaxrsServer = new UndertowJaxrsServer().start(builder);
    final ResteasyDeployment deployment = new ResteasyDeployment();
    deployment.setApplication(jaxRsActivator);
    jaxrsServer.deploy(jaxrsServer.undertowDeployment(deployment)
                                  .setDeploymentName("Rest")
                                  .setContextPath(CONTEXT_PATH_REST)
                                  .setClassLoader(MainRest.class.getClassLoader()));
  }

  public static void stop() {
    try {
      jaxrsServer.stop();
    } catch (Exception e) {
    }

  }
}
