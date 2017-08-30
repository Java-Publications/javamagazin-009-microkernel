package org.rapidpm.microkernel.gui.fx.main;


import static java.lang.Integer.valueOf;

import org.rapidpm.microkernel.api.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by Sven Ruppert on 01.06.2014.
 */
public class MainPaneController {


  private Service service;

  @FXML private Button btn;
  @FXML private TextField inputA;
  @FXML private TextField inputB;
  @FXML private Text message;

  public MainPaneController() {
    System.out.println("MainPaneController = OK");
  }

  private int counter = 0;

  public void onAdd(ActionEvent actionEvent) {
    btn.setText("MainFX pressed " + counter);
    counter = counter + 1;

    message.setText(String.valueOf(
        service.add(valueOf(inputA.getText()) ,
                    valueOf(inputB.getText())))
    );

  }

}
