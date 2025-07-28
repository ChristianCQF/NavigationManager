package com.christianqf.navigationmanager;

import com.christianqf.navigationmanager.core.NavigationController;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

@DesignerComponent(
  version = 111,
  versionName = "3.0.3",
  description = "Advanced Navigation Manager",
  iconName = "ventanas.png"
)
public class NavigationManager extends AndroidNonvisibleComponent {

  private final NavigationController controller = new NavigationController();

  public NavigationManager(ComponentContainer container) {
    super(container.$form());
  }

  @SimpleFunction(description = "Establece la pantalla raíz.")
  public void SetRoot(AndroidViewComponent main) {
    controller.removeRoot();
    controller.setRoot(main);
    OnScreen(main);
  }

  @SimpleFunction(description = "Navega a una pantalla.")
  public void Navigate(AndroidViewComponent main) {
    AndroidViewComponent result = controller.navigateTo(main);
    if (result != null) OnScreen(result);
  }

  @SimpleFunction(description = "Navega a una subpantalla.")
  public void NavigateSub(AndroidViewComponent main, AndroidViewComponent sub) {
    AndroidViewComponent result = controller.navigateToSub(main, sub);
    if (result != null) OnScreen(result);
  }

  @SimpleFunction(description = "Retrocede en la navegación.")
  public void Back() {
    if (!controller.canGoBack()) {
      AndroidViewComponent root = controller.goBack();
      OnRoot(root);
      return;
    }

    AndroidViewComponent previous = controller.goBack();
    if (previous != null) OnBack(previous);
  }

  @SimpleEvent(description = "Se activa una pantalla.")
  public void OnScreen(AndroidViewComponent screen) {
    EventDispatcher.dispatchEvent(this, "OnScreen", screen);
  }

  @SimpleEvent(description = "Se vuelve a una pantalla anterior.")
  public void OnBack(AndroidViewComponent screen) {
    EventDispatcher.dispatchEvent(this, "OnBack", screen);
  }

  @SimpleEvent(description = "Ya no hay más pantallas en la pila.")
  public void OnRoot(AndroidViewComponent screen) {
    EventDispatcher.dispatchEvent(this, "OnRoot", screen);
  }
}
/*

  @SimpleFunction(description = "Establece la pantalla raíz.")
  public void RootScreen(AndroidViewComponent root) {
    controller.removeRoot();
    controller.setRoot(root);
    OnActiveScreen(root);
  }

  @SimpleFunction(description = "Navega a una pantalla.")
  public void NavigateToScreen(AndroidViewComponent main) {
    AndroidViewComponent result = controller.navigateTo(main);
    if (result != null) OnActiveScreen(result);
  }

  @SimpleFunction(description = "Navega a una subpantalla.")
  public void NavigateToSubScreen(
    AndroidViewComponent main,
    AndroidViewComponent sub
  ) {
    AndroidViewComponent result = controller.navigateToSub(main, sub);
    if (result != null) OnActiveScreen(result);
  }

  @SimpleFunction(description = "Retrocede en la navegación.")
  public void GoBack() {
    if (!controller.canGoBack()) {
      AndroidViewComponent root = controller.goBack();
      OnNavigationEnd(root);
      return;
    }

    AndroidViewComponent previous = controller.goBack();
    if (previous != null) OnPreviousScreen(previous);
  }

  @SimpleEvent(description = "Se activa una pantalla.")
  public void OnActiveScreen(AndroidViewComponent component) {
    EventDispatcher.dispatchEvent(this, "OnActiveScreen", component);
  }

  @SimpleEvent(description = "Se vuelve a una pantalla anterior.")
  public void OnPreviousScreen(AndroidViewComponent component) {
    EventDispatcher.dispatchEvent(this, "OnPreviousScreen", component);
  }

  @SimpleEvent(description = "Ya no hay más pantallas en la pila.")
  public void OnNavigationEnd(AndroidViewComponent root) {
    EventDispatcher.dispatchEvent(this, "OnNavigationEnd", root);
  }

 */
