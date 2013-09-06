package com.lumoza.android.imagemenu;

import com.lumoza.android.ImageMenu;

/**
 * Abstract factory for {@link ImageLoader}.
 *
 * @author Maksim Zakharov
 * @since 1.0
 */
public interface ImageLoaderFactory {

    /**
     * Create image loader for given <code>imageMenu</code> to load given <code>url</code>.
     *
     * @param imageMenu image menu to retrieve image for
     * @param url url to retrieve image from
     * @return created image loader
     */
    ImageLoader createLoader(ImageMenu imageMenu, String url);
}
