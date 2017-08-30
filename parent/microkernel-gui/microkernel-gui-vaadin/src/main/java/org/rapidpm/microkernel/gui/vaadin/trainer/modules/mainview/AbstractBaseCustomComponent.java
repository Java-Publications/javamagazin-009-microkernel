package org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 *
 */
public abstract class AbstractBaseCustomComponent extends CustomComponent {

  public AbstractBaseCustomComponent() {
    setCompositionRoot(createComponent());
    setSizeFull();
  }

  protected abstract Component createComponent();
}
