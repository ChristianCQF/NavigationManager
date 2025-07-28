package com.christianqf.navigationmanager.util;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NavigationRegistry {

  private final Set<AndroidViewComponent> screenTypes = new HashSet<>();
  private final Map<AndroidViewComponent, AndroidViewComponent> subScreenTypes =
    new HashMap<>();

  public void registerScreen(AndroidViewComponent component) {
    if (!screenTypes.contains(component)) {
      screenTypes.add(component);
    }
  }

  public void registerSubScreen(
    AndroidViewComponent main,
    AndroidViewComponent sub
  ) {
    registerScreen(main);
    subScreenTypes.putIfAbsent(sub, main);
  }

  public boolean isValidSubScreen(
    AndroidViewComponent main,
    AndroidViewComponent sub
  ) {
    return subScreenTypes.getOrDefault(sub, null) == main;
  }

  public boolean isScreenRegistered(AndroidViewComponent component) {
    return screenTypes.contains(component);
  }

  public void clear() {
    screenTypes.clear();
    subScreenTypes.clear();
  }
}
