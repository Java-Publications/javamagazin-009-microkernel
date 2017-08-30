package org.rapidpm.microkernel.gui.vaadin.trainer.modules.login;

import static org.rapidpm.microkernel.gui.vaadin.trainer.ComponentIDGenerator.buttonID;
import static org.rapidpm.microkernel.gui.vaadin.trainer.ComponentIDGenerator.passwordID;
import static org.rapidpm.microkernel.gui.vaadin.trainer.ComponentIDGenerator.textfieldID;

import java.util.function.Supplier;

import org.rapidpm.microkernel.api.login.LoginService;
import org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview.MainView;
import org.rapidpm.microkernel.rest.client.LoginRestClient;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class LoginComponent extends CustomComponent {

  public static final String BUTTON_CAPTION_OK = "Ok";
  public static final String BUTTON_CAPTION_CANCEL = "Cancel";
  public static final String TEXTFIELD_CAPTION_LOGIN = "Login";
  public static final String PASSWORDFIELD_CAPTION_PASSWORD = "Password";
  public static final String PANEL_CAPTION_MAIN = "Login required";


  public static final String ID_BUTTON_OK = buttonID().apply(LoginComponent.class, BUTTON_CAPTION_OK);
  public static final String ID_BUTTON_CANCEL = buttonID().apply(LoginComponent.class, BUTTON_CAPTION_CANCEL);
  public static final String ID_TEXTFIELD_LOGIN = textfieldID().apply(LoginComponent.class, TEXTFIELD_CAPTION_LOGIN);
  public static final String ID_PASSWORDFIELD_PASSWORD = passwordID().apply(LoginComponent.class, PASSWORDFIELD_CAPTION_PASSWORD);
  public static final String ID_PANEL_MAIN = passwordID().apply(LoginComponent.class, PANEL_CAPTION_MAIN);
  public static final String SESSION_ATTRIBUTE_USER = "user";

  //TODO how to get the instance ??
  private final Supplier<MainView> mainViewSupplier = MainView::new;

  private final LoginService loginService = new LoginRestClient();

  private final TextField login = new TextField();
  private final PasswordField password = new PasswordField();
  private final Button ok = new Button();
  private final Button cancel = new Button();
  private final HorizontalLayout buttons = new HorizontalLayout(ok, cancel);

  private final VerticalLayout layout = new VerticalLayout(login, password, buttons);
  private final Panel panel = new Panel(PANEL_CAPTION_MAIN, layout);

  public LoginComponent() {
    setCompositionRoot(panel);
  }

  public void postProcess() {
    panel.setId(ID_PANEL_MAIN);
    panel.setCaption(PANEL_CAPTION_MAIN);
    panel.setSizeFull();

    login.setId(ID_TEXTFIELD_LOGIN);
    login.setCaption(TEXTFIELD_CAPTION_LOGIN); //TODO i18n

    password.setId(ID_PASSWORDFIELD_PASSWORD);
    password.setCaption(PASSWORDFIELD_CAPTION_PASSWORD); //TODO i18n

    ok.setCaption(BUTTON_CAPTION_OK);
    ok.setId(ID_BUTTON_OK);
    ok.addClickListener((Button.ClickListener) event -> {
      boolean isValid = loginService.check(login.getValue(), password.getValue());
      clearInputFields();

      if (isValid) {
        UI.getCurrent()
          .getSession()
          .setAttribute(SESSION_ATTRIBUTE_USER, loginService.loadUser(login.getValue()));
      }

      UI.getCurrent()
        .setContent((isValid) ? mainViewSupplier.get() : this);
    });

    cancel.setId(ID_BUTTON_CANCEL);
    cancel.setCaption(BUTTON_CAPTION_CANCEL);
    cancel.addClickListener((Button.ClickListener) event -> clearInputFields());
  }

  private void clearInputFields() {
    login.setValue("");
    password.setValue("");
  }
}
