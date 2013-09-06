package com.lumoza.android.imagemenu;

import android.graphics.Bitmap;

/**
 * Image loaded listener.
 *
 * @author Maksim Zakharov
 * @since 1.0
 */
public interface ImageLoadedListener {

    /**
     * Image loaded callback method.
     *
     * @param bitmap retrieved bitmap
     */
    void onLoad(Bitmap bitmap);
}
