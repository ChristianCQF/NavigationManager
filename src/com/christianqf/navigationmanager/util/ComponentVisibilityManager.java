package com.christianqf.navigationmanager.util;

import android.view.View;
import com.google.appinventor.components.runtime.AndroidViewComponent;

public class ComponentVisibilityManager {

  public static void hide(AndroidViewComponent component) {
    if (component != null) {
      component.getView().setVisibility(View.GONE);
    }
  }

  public static void show(AndroidViewComponent component) {
    if (component != null) {
      component.getView().setVisibility(View.VISIBLE);
    }
  }
}
