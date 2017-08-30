package org.rapidpm.microkernel.rest.client;

import java.io.IOException;

import org.rapidpm.frp.model.Result;
import org.rapidpm.microkernel.api.login.LoginService;
import org.rapidpm.microkernel.api.login.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */
public class LoginRestClient implements LoginService {

  @Override
  public Boolean check(String login , String password) {

    OkHttpClient client = new OkHttpClient();

    //here hard coded
    // http://127.0.0.1:7081/rest/api/add?inputA=1&inputB=2
    Request request = new Request.Builder()
        .url("http://127.0.0.1:7081/rest/security/check?username="+login+"&password="+password)
        .build();

    final String result;
    try {
      Response response = client.newCall(request).execute();
      result = response.body().string();
      return Boolean.parseBoolean(result);
    } catch (IOException e) {
      e.printStackTrace();
      return Boolean.FALSE;
    }


  }

  @Override
  public Result<User> loadUser(String login) {
    return null;
  }
}
