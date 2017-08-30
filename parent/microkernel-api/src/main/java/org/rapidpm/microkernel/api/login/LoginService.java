package org.rapidpm.microkernel.api.login;

import org.rapidpm.frp.model.Result;

/**
 *
 */
public interface LoginService {

  Boolean check(String login , String password);

  Result<User> loadUser(String login);

}
