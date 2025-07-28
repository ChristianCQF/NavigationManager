package com.christianqf.navigationmanager.util;

import com.google.appinventor.components.runtime.AndroidViewComponent;

public class NavigationEntry {

  private final AndroidViewComponent main;
  private final AndroidViewComponent sub;

  public NavigationEntry(AndroidViewComponent main) {
    this.main = main;
    this.sub = null;
  }

  public NavigationEntry(AndroidViewComponent main, AndroidViewComponent sub) {
    this.main = main;
    this.sub = sub;
  }

  public boolean isSubScreen() {
    return sub != null;
  }

  public AndroidViewComponent getMain() {
    return main;
  }

  public AndroidViewComponent getSub() {
    return sub;
  }
}
