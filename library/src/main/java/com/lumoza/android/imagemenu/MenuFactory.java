package com.lumoza.android.imagemenu;

import android.content.Context;
import com.lumoza.android.ImageMenu;

import java.util.List;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public interface MenuFactory {

    void makeMenu(Context context, ImageMenu imageMenu, List<Action> actions);
}
