package com.app.homelauncher.activity.homeparts;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;

import com.app.homelauncher.R;
import com.app.homelauncher.activity.HomeActivity;
import com.app.homelauncher.interfaces.DialogListener;
import com.app.homelauncher.manager.Setup;
import com.app.homelauncher.model.Item;
import com.app.homelauncher.util.Definitions.ItemPosition;
import com.app.homelauncher.util.Tool;
import com.app.homelauncher.widget.Desktop;
import com.app.homelauncher.widget.Dock;
import com.app.homelauncher.widget.WidgetContainer;

public class HpItemOption implements DialogListener.OnEditDialogListener {
    private HomeActivity _homeActivity;
    private Item _item;

    public HpItemOption(HomeActivity homeActivity) {
        _homeActivity = homeActivity;
    }

    public void onEditItem(final Item item) {
        _item = item;
        Setup.eventHandler().showEditDialog(_homeActivity, item, this);
    }

    public final void onUninstallItem(@NonNull Item item) {
        _homeActivity.ignoreResume = true;
        Setup.eventHandler().showDeletePackageDialog(_homeActivity, item);
    }

    public final void onRemoveItem(@NonNull Item item) {
        View coordinateToChildView;
        if (item._location.equals(ItemPosition.Group)) {
            Tool.toast(_homeActivity, R.string.toast_remove_from_group_first);
            return;
        } else if (item._location.equals(ItemPosition.Desktop)) {
            Desktop desktop = _homeActivity.getDesktop();
            coordinateToChildView = desktop.getCurrentPage().coordinateToChildView(new Point(item._x, item._y));
            desktop.removeItem(coordinateToChildView, true);
        } else {
            Dock dock = _homeActivity.getDock();
            coordinateToChildView = dock.coordinateToChildView(new Point(item._x, item._y));
            dock.removeItem(coordinateToChildView, true);
        }
        _homeActivity._db.deleteItem(item, true);
    }

    public final void onInfoItem(@NonNull Item item) {
        if (item._type == Item.Type.APP) {
            try {
                String str = "android.settings.APPLICATION_DETAILS_SETTINGS";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("package:");
                Intent intent = item._intent;
                ComponentName component = intent.getComponent();
                stringBuilder.append(component.getPackageName());
                _homeActivity.startActivity(new Intent(str, Uri.parse(stringBuilder.toString())));
            } catch (Exception e) {
                Tool.toast(_homeActivity, R.string.toast_app_uninstalled);
            }
        }
    }

    public final void onResizeItem(@NonNull Item item) {
        View coordinateToChildView;
        if (item._location.equals(ItemPosition.Desktop)) {
            Desktop desktop = _homeActivity.getDesktop();
            coordinateToChildView = desktop.getCurrentPage().coordinateToChildView(new Point(item._x, item._y));
        } else {
            Dock dock = _homeActivity.getDock();
            coordinateToChildView = dock.coordinateToChildView(new Point(item._x, item._y));
        }

        if (coordinateToChildView != null) {
            ((WidgetContainer) coordinateToChildView).showResize();
        }
    }

    @Override
    public void onRename(String name) {
        _item.setLabel(name);
        Setup.dataManager().saveItem(_item);
        Point point = new Point(_item._x, _item._y);

        if (_item._location.equals(ItemPosition.Group)) {
            return;
        } else if (_item._location.equals(ItemPosition.Desktop)) {
            Desktop desktop = _homeActivity.getDesktop();
            desktop.removeItem(desktop.getCurrentPage().coordinateToChildView(point), false);
            desktop.addItemToCell(_item, _item._x, _item._y);
        } else {
            Dock dock = _homeActivity.getDock();
            _homeActivity.getDock().removeItem(dock.coordinateToChildView(point), false);
            dock.addItemToCell(_item, _item._x, _item._y);
        }
    }
}
