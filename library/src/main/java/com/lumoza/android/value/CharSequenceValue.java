package com.lumoza.android.value;

import android.content.Context;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class CharSequenceValue implements Value<CharSequence> {

    private final CharSequence value;

    public CharSequenceValue(CharSequence value) {
        this.value = value;
    }

    @Override
    public CharSequence resolve(Context context) {
        return value;
    }
}
