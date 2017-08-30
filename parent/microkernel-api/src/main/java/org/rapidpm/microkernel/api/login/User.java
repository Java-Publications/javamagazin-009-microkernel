package org.rapidpm.microkernel.api.login;

import org.rapidpm.frp.model.Tripel;

/**
 *
 */
public class User extends Tripel<String, String, String> {

  public User(String login , String foreName , String familyName) {
    super(login , foreName , familyName);
  }

  public String login() { return getT1();}

  public String foreName() {return getT2();}

  public String familyName() {return getT3();}

}
