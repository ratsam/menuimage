package com.lumoza.android.imagemenu.aq;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.lumoza.android.imagemenu.ImageLoadErrorListener;
import com.lumoza.android.imagemenu.ImageLoadedListener;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class CustomBitmapAjaxCallback extends BitmapAjaxCallback {

    private final ImageLoadErrorListener errorListener;
    private final ImageLoadedListener loadedListener;

    public CustomBitmapAjaxCallback(ImageLoadErrorListener errorListener, ImageLoadedListener loadedListener) {
        this.errorListener = errorListener;
        this.loadedListener = loadedListener;
    }

    @Override
    protected void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
        super.callback(url, iv, bm, status);

        if (status.getCode() != 200) {
            errorListener.onError();
        } else {
            loadedListener.onLoad(bm);
        }
    }
}
