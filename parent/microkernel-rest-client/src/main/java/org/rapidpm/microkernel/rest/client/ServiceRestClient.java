package org.rapidpm.microkernel.rest.client;

import java.io.IOException;

import org.rapidpm.microkernel.api.Service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class ServiceRestClient implements Service {

  // here problem - Fehlerfall
  @Override
  public int add(int a , int b) {
    OkHttpClient client = new OkHttpClient();

    //here hard coded
    // http://127.0.0.1:7081/rest/api/add?inputA=1&inputB=2
    Request request = new Request.Builder()
        .url("http://127.0.0.1:7081/rest/api/add?inputA="+a+"&inputB="+b)
        .build();

    final String result;
    try {
      Response response = client.newCall(request).execute();
      result = response.body().string();
      return Integer.parseInt(result);
    } catch (IOException e) {
      e.printStackTrace();
      return Integer.MIN_VALUE;
    }

  }
}
