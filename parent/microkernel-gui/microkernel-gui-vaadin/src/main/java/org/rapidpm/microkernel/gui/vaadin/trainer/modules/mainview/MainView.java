package org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview;


import static org.rapidpm.microkernel.gui.vaadin.trainer.ComponentIDGenerator.buttonID;
import static org.rapidpm.microkernel.gui.vaadin.trainer.modules.login.LoginComponent.SESSION_ATTRIBUTE_USER;

import java.util.function.Supplier;

import org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview.calc.CalcComponent;
import org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview.dashboard.DashboardComponent;
import org.rapidpm.microkernel.gui.vaadin.trainer.modules.mainview.write.WriteComponent;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
public class MainView extends CustomComponent {


  public MainView() {
    setCompositionRoot(mainLayout());
    setSizeFull();
  }

  private VerticalLayout menuLayout;
  private CssLayout contentLayout;

  private HorizontalLayout mainLayout() {
    menuLayout = new VerticalLayout();
    menuLayout.setStyleName(ValoTheme.MENU_ROOT);
    menuLayout.setWidth(100, Unit.PERCENTAGE);
    menuLayout.setHeight(100, Unit.PERCENTAGE);
    menuLayout.setSizeFull();

    final CssLayout menuButtons = new CssLayout();
    menuButtons.setSizeFull();
    menuButtons.addStyleName(ValoTheme.MENU_PART);
    menuButtons.addStyleName(ValoTheme.MENU_PART_LARGE_ICONS);

    menuButtons.addComponents(
        createMenuButton(VaadinIcons.VIEWPORT, "Dashboard", DashboardComponent::new),
        createMenuButton(VaadinIcons.SITEMAP, "Sitemap", DashboardComponent::new),
        createMenuButton(VaadinIcons.CALC_BOOK, "Calculate", CalcComponent::new),
        createMenuButton(VaadinIcons.NOTEBOOK, "Write", WriteComponent::new),
        createMenuButtonForNotification(VaadinIcons.EXIT, "Logout", "You want to go?")
    );
    menuLayout.addComponent(menuButtons);

    contentLayout = new CssLayout(new Label("Content"));
    contentLayout.setSizeFull();

    final HorizontalLayout layout = new HorizontalLayout();
    layout.setSizeFull();
    layout.addComponent(menuLayout);
    layout.addComponent(contentLayout);

    layout.setExpandRatio(menuLayout, 0.20f);
    layout.setExpandRatio(contentLayout, 0.80f);

    return layout;
  }




  //TODO more generic - refactoring
  private Button createMenuButtonForNotification(VaadinIcons icon, String caption, String message) {
    final Button button
        = new Button(caption,
                     (e) -> {
                       UI ui = UI.getCurrent();
                       ConfirmDialog.show(
                           ui,
                           message, // ToDo extract in Executor
                           (ConfirmDialog.Listener) dialog -> {
                             if (dialog.isConfirmed()) {
                               VaadinSession vaadinSession = ui.getSession();
                               vaadinSession.setAttribute(SESSION_ATTRIBUTE_USER, null);
                               vaadinSession.close();
                               ui.getPage().setLocation("/");
                             }
                             else {
                               // User did not confirm
                               // CANCEL STUFF
                             }
                           });
                     });

    button.setIcon(icon);
    button.addStyleName(ValoTheme.BUTTON_HUGE);
    button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
    button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
    button.addStyleName(ValoTheme.MENU_ITEM);
    button.setWidth("100%");

    button.setId(buttonID().apply(MainView.class, caption));

    return button;

  }


  private Button createMenuButton(VaadinIcons icon, String caption, Supplier<CustomComponent> content) {

    final Button button = new Button(caption, (e) -> {
      contentLayout.removeAllComponents();
      contentLayout.addComponent(content.get());
    });
    button.setIcon(icon);
    button.addStyleName(ValoTheme.BUTTON_HUGE);
    button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
    button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
    button.addStyleName(ValoTheme.MENU_ITEM);
    button.setWidth("100%");

    button.setId(buttonID().apply(MainView.class, caption));

    return button;
  }

}
