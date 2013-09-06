package com.lumoza.android.imagemenu.aq;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;
import com.androidquery.AQuery;
import com.lumoza.android.ImageMenu;
import com.lumoza.android.imagemenu.ImageLoadErrorListener;
import com.lumoza.android.imagemenu.ImageLoadedListener;
import com.lumoza.android.imagemenu.ImageLoader;

/**
 * Android Query based image loader.
 * @author Maksim Zakharov
 * @since 1.0
 */
public class ImageLoaderAQImpl implements ImageLoader, ImageLoadErrorListener, ImageLoadedListener {

    private final boolean offline;
    private final String url;

    private final ImageMenu imageMenu;

    private final AQuery aq;

    public ImageLoaderAQImpl(boolean offline, String url, ImageMenu imageMenu) {
        this.offline = offline;
        this.url = url;

        this.imageMenu = imageMenu;

        this.aq = new AQuery(imageMenu);
    }

    @Override
    public void reload() {
        reset();
        loadUrl(); // Force load, don't check 'offline' state
    }

    public void load() {
        reset();

        if (offline) {
            showOffline();
        } else {
            loadUrl();
        }
    }

    private void showOffline() {
        final Bitmap bitmap = aq.getCachedImage(url);
        if (bitmap != null) {
            showCached(bitmap);
        } else {
            onError();
        }
    }

    private void showCached(Bitmap bitmap) {
        imageMenu.setImageBitmap(bitmap);
        imageMenu.onLoad();
    }

    private void loadUrl() {
        final AQuery q = aq.id(imageMenu.getResultView());

        final CustomBitmapAjaxCallback bitmapAjax = new CustomBitmapAjaxCallback(this, this);
        final View progressView = imageMenu.getProgressView();
        if (progressView instanceof ProgressBar) {
            bitmapAjax.progress(progressView);
        }
        bitmapAjax.url(url);
        q.image(bitmapAjax);
    }

    @Override
    public void onError() {
        imageMenu.onError();
    }

    @Override
    public void onLoad(Bitmap bitmap) {
        imageMenu.setImageBitmap(bitmap);
        imageMenu.onLoad();
    }

    private void reset() {
        imageMenu.onLoadStart();
    }
}
