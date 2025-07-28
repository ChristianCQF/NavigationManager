package com.christianqf.navigationmanager.core;

import com.christianqf.navigationmanager.util.ComponentVisibilityManager;
import com.christianqf.navigationmanager.util.NavigationEntry;
import com.christianqf.navigationmanager.util.NavigationRegistry;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import java.util.ArrayList;
import java.util.List;

public class NavigationController {

  private final List<NavigationEntry> history = new ArrayList<>();
  private final NavigationRegistry registry = new NavigationRegistry();
  private AndroidViewComponent rootScreen = null;

  public void setRoot(AndroidViewComponent root) {
    registry.registerScreen(root);
    history.clear();
    history.add(new NavigationEntry(root));
    ComponentVisibilityManager.show(root);
    rootScreen = root;
  }

  public void removeRoot() {
    // Ocultar y limpiar todas las vistas en el historial
    for (NavigationEntry entry : history) {
      ComponentVisibilityManager.hide(entry.getMain());
      if (entry.isSubScreen()) {
        ComponentVisibilityManager.hide(entry.getSub());
      }
    }

    // Limpiar historial
    history.clear();
    registry.clear();
    rootScreen = null;
  }

  public AndroidViewComponent navigateTo(AndroidViewComponent screen) {
    registry.registerScreen(screen);
    history.forEach(entry -> ComponentVisibilityManager.hide(entry.getMain()));
    NavigationEntry entry = new NavigationEntry(screen);
    history.add(entry);
    ComponentVisibilityManager.show(screen);
    return screen;
  }

  public AndroidViewComponent navigateToSub(
    AndroidViewComponent main,
    AndroidViewComponent sub
  ) {
    registry.registerSubScreen(main, sub);
    if (!registry.isValidSubScreen(main, sub)) return null;

    if (!history.isEmpty()) {
      NavigationEntry last = history.get(history.size() - 1);
      ComponentVisibilityManager.hide(last.getMain());
      if (last.isSubScreen()) ComponentVisibilityManager.hide(last.getSub());
    }

    NavigationEntry entry = new NavigationEntry(main, sub);
    history.add(entry);

    ComponentVisibilityManager.show(main);
    ComponentVisibilityManager.show(sub);
    return sub;
  }

  public AndroidViewComponent goBack() {
    if (history.size() <= 1) return rootScreen;

    NavigationEntry current = history.remove(history.size() - 1);
    ComponentVisibilityManager.hide(current.getMain());
    if (current.isSubScreen()) ComponentVisibilityManager.hide(
      current.getSub()
    );

    NavigationEntry previous = history.get(history.size() - 1);
    ComponentVisibilityManager.show(previous.getMain());
    if (previous.isSubScreen()) {
      ComponentVisibilityManager.show(previous.getSub());
      return previous.getSub();
    } else {
      return previous.getMain();
    }
  }

  public boolean canGoBack() {
    return history.size() > 1;
  }

  public NavigationRegistry getRegistry() {
    return registry;
  }
}
