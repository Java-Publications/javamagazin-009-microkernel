package org.rapidpm.microkernel.gui.fx;

import org.rapidpm.microkernel.gui.fx.login.LoginPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

  public static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception {

    MainFX.primaryStage = primaryStage;

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(
        new Scene(
            new LoginPane() , 300 , 275));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
