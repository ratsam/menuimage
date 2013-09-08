package com.lumoza.android.imagemenu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import com.lumoza.android.ImageMenu;

import java.util.List;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class PopupMenuFactory implements MenuFactory {

    @Override
    public void makeMenu(Context context, final ImageMenu imageMenu, final List<Action> actions) {
        final PopupMenu popup = new PopupMenu(context, imageMenu);
        for (Action action : actions) {
            popup.getMenu().add(0, action.getId(), Menu.NONE, action.resolveTitle(context));
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final int id = item.getItemId();
                // O(n)
                for (Action action : actions) {
                    if (action.getId() == id) {
                        action.getListener().onClick(imageMenu, id);
                        return true;
                    }
                }

                return false;
            }
        });
        popup.show();
    }
}
