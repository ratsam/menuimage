package com.lumoza.android.imagemenu;

import android.content.Context;
import com.lumoza.android.value.CharSequenceResource;
import com.lumoza.android.value.CharSequenceValue;
import com.lumoza.android.value.Value;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class Action {

    private int id;
    private Value<CharSequence> title;
    private MenuItemClickListener listener;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(int titleResource) {
        this.title = new CharSequenceResource(titleResource);
    }

    public void setTitle(CharSequence title) {
        this.title = new CharSequenceValue(title);
    }

    public CharSequence resolveTitle(Context context) {
        return title.resolve(context);
    }

    public MenuItemClickListener getListener() {
        return listener;
    }

    public void setListener(MenuItemClickListener listener) {
        this.listener = listener;
    }
}
