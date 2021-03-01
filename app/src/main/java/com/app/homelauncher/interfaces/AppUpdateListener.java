package com.app.homelauncher.interfaces;

import com.app.homelauncher.model.App;

import java.util.List;

public interface AppUpdateListener {
    boolean onAppUpdated(List<App> apps);
}
