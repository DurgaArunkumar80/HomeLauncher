package com.app.homelauncher.activity.homeparts;

import android.content.Context;
import android.support.annotation.NonNull;

import com.app.homelauncher.AppObject;
import com.app.homelauncher.activity.HomeActivity;
import com.app.homelauncher.manager.Setup;
import com.app.homelauncher.util.AppManager;
import com.app.homelauncher.util.AppSettings;
import com.app.homelauncher.util.DatabaseHelper;
import com.app.homelauncher.viewutil.DesktopGestureListener.DesktopGestureCallback;

public final class HpInitSetup extends Setup {
    private final AppManager _appLoader;
    private final DatabaseHelper _dataManager;
    private final HpGestureCallback _desktopGestureCallback;
    private final HpEventHandler _eventHandler;
    private final AppSettings _appSettings;

    public HpInitSetup(HomeActivity homeActivity) {
        _appSettings = AppSettings.get();
        _desktopGestureCallback = new HpGestureCallback(_appSettings);
        _dataManager = new DatabaseHelper(homeActivity);
        _appLoader = AppManager.getInstance(homeActivity);
        _eventHandler = new HpEventHandler();
    }

    @NonNull
    public Context getAppContext() {
        return AppObject.get();
    }

    @NonNull
    public AppSettings getAppSettings() {
        return _appSettings;
    }

    @NonNull
    public DesktopGestureCallback getDesktopGestureCallback() {
        return _desktopGestureCallback;
    }

    @NonNull
    public DatabaseHelper getDataManager() {
        return _dataManager;
    }

    @NonNull
    public AppManager getAppLoader() {
        return _appLoader;
    }

    @NonNull
    public EventHandler getEventHandler() {
        return _eventHandler;
    }
}