package com.app.homelauncher.viewutil;

import android.view.View;

import com.app.homelauncher.interfaces.ItemHistory;
import com.app.homelauncher.model.Item;

public interface DesktopCallback extends ItemHistory {
    boolean addItemToPoint(Item item, int x, int y);

    boolean addItemToPage(Item item, int page);

    boolean addItemToCell(Item item, int x, int y);

    void removeItem(View view, boolean animate);
}
