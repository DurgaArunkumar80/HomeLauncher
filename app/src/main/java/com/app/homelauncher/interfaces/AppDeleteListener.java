package com.app.homelauncher.interfaces;

import com.app.homelauncher.model.App;

import java.util.List;

public interface AppDeleteListener {
    boolean onAppDeleted(List<App> apps);
}
