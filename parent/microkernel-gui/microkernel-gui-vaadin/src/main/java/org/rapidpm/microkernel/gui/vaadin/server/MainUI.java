package org.rapidpm.microkernel.gui.vaadin.server;

import org.rapidpm.frp.model.Result;
import org.rapidpm.microkernel.api.login.User;
import org.rapidpm.microkernel.gui.vaadin.trainer.modules.login.LoginComponent;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("valo")
@PreserveOnRefresh
public class MainUI extends UI {

  @Override
  protected void init(VaadinRequest request) {
    System.out.println("init - request = " + request);
    if(! user().isPresent()) setContent(login());
    setSizeFull();
  }

  @Override
  protected void refresh(VaadinRequest request) {
    super.refresh(request);
    System.out.println("refresh - request = " + request);
  }

  private Result<User> user() {
    return Result
        .ofNullable(
            getCurrent()
                .getSession()
                .getAttribute(User.class));
  }

  private LoginComponent login() {
    LoginComponent content = new LoginComponent();
    content.postProcess();
    return content;
  }

}
