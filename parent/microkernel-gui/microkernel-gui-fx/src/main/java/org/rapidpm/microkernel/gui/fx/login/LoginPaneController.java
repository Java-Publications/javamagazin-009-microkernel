package org.rapidpm.microkernel.gui.fx.login;

import org.rapidpm.microkernel.gui.fx.MainFX;
import org.rapidpm.microkernel.gui.fx.main.MainPane;
import org.rapidpm.microkernel.rest.client.LoginRestClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 */
public class LoginPaneController {
  public LoginPane loginPane;

//  private LoginService loginService = new LoginService() {
//    @Override
//    public Boolean check(String login , String password) {
//      return (login != null && login.equals("admin") &&
//              (password != null && password.equals("admin")));
//    }
//  };


  @FXML private PasswordField passwordField;
  @FXML private TextField username;

  @FXML private Text message;

  @FXML
  protected void onLogin(ActionEvent event) {

    final Boolean check = new LoginRestClient().check(username.getText() , passwordField.getText());
    if (check) {
      final Stage primaryStage = MainFX.primaryStage;
      primaryStage.setTitle("Logged in");
      primaryStage.setScene(new Scene(new MainPane() , 640 , 480));
      primaryStage.show();
    } else {
      message.setText("Login failed ");
    }


  }


}
