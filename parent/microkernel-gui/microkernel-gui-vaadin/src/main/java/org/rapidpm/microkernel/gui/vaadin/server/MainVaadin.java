package org.rapidpm.microkernel.gui.vaadin.server;


import static io.undertow.Handlers.redirect;
import static io.undertow.servlet.Servlets.servlet;

import javax.servlet.ServletException;

import org.rapidpm.frp.model.Result;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

/**
 *
 */
public class MainVaadin {

  public static final String CONTEXT_PATH = "/";

  public static void start() {
    main(new String[0]);
  }

  public static void shutdown() {
    undertow.ifPresent(Undertow::stop);
  }

  private static Result<Undertow> undertow;

  public static void main(String[] args) {

    DeploymentInfo servletBuilder
        = Servlets.deployment()
                  .setClassLoader(MainVaadin.class.getClassLoader())
                  .setContextPath(CONTEXT_PATH)
                  .setDeploymentName("ROOT.war")
                  .setDefaultEncoding("UTF-8")
                  .addServlets(
                      servlet(
                          MainServlet.class.getSimpleName(),
                          MainServlet.class).addMapping("/*")
                  );

    DeploymentManager manager = Servlets
        .defaultContainer()
        .addDeployment(servletBuilder);

    manager.deploy();

    try {
      HttpHandler httpHandler = manager.start();
      PathHandler path = Handlers.path(redirect(CONTEXT_PATH))
                                 .addPrefixPath(CONTEXT_PATH, httpHandler);

      Undertow undertowServer = Undertow.builder()
                                        .addHttpListener(8080, "0.0.0.0")
                                        .setHandler(path)
                                        .build();
      undertowServer.start();

      undertow = Result.success(undertowServer);

      undertowServer.getListenerInfo().forEach(System.out::println);

    } catch (ServletException e) {
      e.printStackTrace();
      undertow = Result.failure(e.getMessage());
    }
  }
}
