package com.lumoza.android.imagemenu;

/**
 * Static holder for {@link ImageLoaderFactory}.
 *
 * @author Maksim Zakharov
 * @since 1.0
 */
@Deprecated
public class ImageLoaderFactoryHolder {

    private static volatile ImageLoaderFactory factory;

    public static ImageLoaderFactory getFactory() {
        return factory;
    }

    public static void setFactory(ImageLoaderFactory factory) {
        ImageLoaderFactoryHolder.factory = factory;
    }

    /**
     * Private constructor for 'static' class.
     */
    private ImageLoaderFactoryHolder() {

    }
}
