package com.app.homelauncher.interfaces;

import android.view.View;

import com.app.homelauncher.model.Item;

public interface ItemHistory {
    void setLastItem(Item item, View view);

    void revertLastItem();

    void consumeLastItem();
}
