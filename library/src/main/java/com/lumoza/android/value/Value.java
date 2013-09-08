package com.lumoza.android.value;

import android.content.Context;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public interface Value<T> {

    T resolve(Context context);
}
