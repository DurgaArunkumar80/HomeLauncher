package com.app.homelauncher.util;

public class DragAction {
    public Action action;

    public DragAction(Action action) {
        this.action = action;
    }

    public enum Action {
        DESKTOP, DRAWER, SEARCH
    }
}
