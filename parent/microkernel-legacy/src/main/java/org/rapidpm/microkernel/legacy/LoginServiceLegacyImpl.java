package org.rapidpm.microkernel.legacy;

import java.util.Objects;

import org.rapidpm.frp.model.Result;
import org.rapidpm.microkernel.api.login.LoginService;
import org.rapidpm.microkernel.api.login.User;

/**
 *
 */
public class LoginServiceLegacyImpl implements LoginService {
  @Override
  public Boolean check(String login , String password) {
    return ! Objects.isNull(login) && ! Objects.isNull(password)
           && (
               (login.equals("admin") && password.equals("admin"))
               || (login.equals("max") && password.equals("max"))

           );
  }

  @Override
  public Result<User> loadUser(String login) {
    if (login.equals("admin")) return Result.success(new User("admin","Admin","Secure"));
    if (login.equals("max")) return Result.success(new User("max","Max","Rimkus"));
    return Result.failure("no atch..");
  }

}
