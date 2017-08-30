package org.rapidpm.microkernel.gui.vaadin.trainer;

import java.util.Locale;
import java.util.function.BiFunction;

import org.rapidpm.frp.functions.TriFunction;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 *
 */
public interface ComponentIDGenerator {
  static TriFunction<Class, Class, String, String> genericID() {
    return (uiClass, componentClass, label)
        -> (uiClass.getSimpleName()
            + "-" + componentClass.getSimpleName()
            + "-" + label.replace(" ", "-"))
        .toLowerCase(Locale.US);
  }

  static BiFunction<Class, String, String> buttonID() {
    return (uiClass, label) -> genericID().apply(uiClass, Button.class, label);
  }

  static BiFunction<Class, String, String> textfieldID() {
    return (uiClass, label) -> genericID().apply(uiClass, TextField.class, label);
  }

  static BiFunction<Class, String, String> passwordID() {
    return (uiClass, label) -> genericID().apply(uiClass, PasswordField.class, label);
  }
}
