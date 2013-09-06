package com.lumoza.android.imagemenu.aq;

import android.content.SharedPreferences;
import com.lumoza.android.ImageMenu;
import com.lumoza.android.imagemenu.ImageLoaderFactory;

/**
 * Android Query based {@link com.lumoza.android.imagemenu.ImageLoaderFactory} implementation.
 *
 * @author Maksim Zakharov
 * @since 1.0
 */
public class ImageLoaderFactoryAQImpl implements ImageLoaderFactory {

    private final SharedPreferences preferences;

    public ImageLoaderFactoryAQImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    /**
     * Create an Android Query based image loader instance and initiate image loading.
     *
     * @param imageMenu image menu to retrieve image for
     * @param url url to retrieve image from
     * @return Android Query based image loader instance
     */
    @Override
    public ImageLoaderAQImpl createLoader(ImageMenu imageMenu, String url) {
        final ImageLoaderAQImpl imageLoader = new ImageLoaderAQImpl(isOffline(), url, imageMenu);
        imageLoader.load();
        return imageLoader;
    }

    private boolean isOffline() {
        return preferences.getBoolean("doNotLoadImages", false);
    }
}
