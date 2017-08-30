package junit.org.rapidpm.microkernel.undertow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.test.TestPortProvider;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rapidpm.microkernel.undertow.MainRest;

/**
 *
 */
public class ServiceRESTTest {

  @BeforeEach
  void setUp() {
    MainRest.main(null);
  }

  @AfterEach
  void tearDown() {
    MainRest.stop();
  }

  @Test
  void test001() {
    final String generateBasicReqURL = TestPortProvider.generateURL("/rest" + "/api");
    System.out.println("generateBasicReqURL = " + generateBasicReqURL);
    Client client = ClientBuilder.newClient();
    final Invocation.Builder request = client
        .target(generateBasicReqURL)
        .path("add")
        .queryParam("inputA", "1")
        .queryParam("inputB", "1")
        .request();
    final Response response = request.get();
    Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    Assert.assertEquals("2", response.readEntity(String.class));
  }
}
