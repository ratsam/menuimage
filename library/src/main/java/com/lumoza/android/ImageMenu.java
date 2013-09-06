package com.lumoza.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lumoza.android.imagemenu.ImageLoader;
import com.lumoza.android.imagemenu.ImageLoaderFactoryHolder;
import com.lumoza.android.imagemenu.LoadStatus;
import com.lumoza.android.imagemenu.R;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class ImageMenu extends RelativeLayout {

    private Context mContext;
    private LayoutInflater mInflater;

    private View errorView;
    private ImageView resultView;
    private View inProgressView;

    private ImageLoader imageLoader;

    public ImageMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public ImageMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        errorView = getInflater().inflate(R.layout.imm__error, this, false);
        errorView.setVisibility(View.GONE);
        addView(errorView);

        resultView = (ImageView) getInflater().inflate(R.layout.imm__result, this, false);
        addView(resultView);

        inProgressView = getInflater().inflate(R.layout.imm__progress, this, false);
        inProgressView.setVisibility(View.GONE);
        addView(inProgressView);
    }

    public void load(String url) {
        imageLoader = ImageLoaderFactoryHolder.getFactory().createLoader(this, url);
    }

    public LoadStatus getStatus() {
        if (imageLoader != null) {
            return imageLoader.getStatus();
        } else {
            throw new IllegalStateException("ImageMenu#load() method was never called");
        }
    }

    public ImageView getResultView() {
        return resultView;
    }

    public View getProgressView() {
        return inProgressView;
    }

    private LayoutInflater getInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(mContext);
        }
        return mInflater;
    }

    public void onError() {
        inProgressView.setVisibility(View.GONE);
        if (errorView != null) {
            errorView.setVisibility(View.VISIBLE);
        }
    }

    public void onLoad(Bitmap bitmap) {
        inProgressView.setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.imm__image_view)).setImageBitmap(bitmap);
    }

    public void onLoadStart() {
        inProgressView.setVisibility(View.VISIBLE);

        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
    }
}
