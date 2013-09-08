package com.lumoza.android.value;

import android.content.Context;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class CharSequenceResource implements Value<CharSequence> {

    private final int resource;

    public CharSequenceResource(int resource) {
        this.resource = resource;
    }

    @Override
    public CharSequence resolve(Context context) {
        return context.getText(resource);
    }
}
