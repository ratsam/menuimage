package com.lumoza.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.lumoza.android.imagemenu.Action;
import com.lumoza.android.imagemenu.ImageLoader;
import com.lumoza.android.imagemenu.ImageLoaderFactoryHolder;
import com.lumoza.android.imagemenu.LoadStatus;
import com.lumoza.android.imagemenu.MenuFactoryHolder;
import com.lumoza.android.imagemenu.MenuItemClickListener;
import com.lumoza.android.imagemenu.R;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class ImageMenu extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;

    private View errorView;
    private ImageView resultView;
    private View inProgressView;

    private ImageLoader imageLoader;
    private List<Action> actions = new LinkedList<Action>();

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

        inflateViews();
    }

    private void inflateViews() {
        errorView = getInflater().inflate(R.layout.imm__error, this, false);
        errorView.setVisibility(View.GONE);
        addView(errorView);

        resultView = (ImageView) getInflater().inflate(R.layout.imm__result, this, false);
        addView(resultView);

        inProgressView = getInflater().inflate(R.layout.imm__progress, this, false);
        inProgressView.setVisibility(View.GONE);
        addView(inProgressView);
    }

    public void addAction(int id, CharSequence title, MenuItemClickListener listener) {
        final Action action = new Action();
        action.setId(id);
        action.setTitle(title);
        action.setListener(listener);
        actions.add(action);
    }

    public void addAction(int id, int titleResource, MenuItemClickListener listener) {
        final Action action = new Action();
        action.setId(id);
        action.setTitle(titleResource);
        action.setListener(listener);
        actions.add(action);
    }

    @Override
    public void onClick(View v) {
        final LoadStatus status = getStatus();

        final List<Action> clickActions = new LinkedList<Action>(actions);

        if (status == LoadStatus.ERROR) {
            clickActions.add(0, getReloadAction());
        } else if (clickActions.size() == 1) {
            // If there is only one action, force using it and exit.
            final Action onlyAction = clickActions.get(0);
            onlyAction.getListener().onClick(this, onlyAction.getId());
            return;
        }

        MenuFactoryHolder.getFactory().makeMenu(getContext(), this, clickActions);
    }

    private Action getReloadAction() {
        final Action reload = new Action();
        reload.setId(-501);
        reload.setTitle(R.string.imm__reload);
        reload.setListener(null); // TODO: listen and reload
        return reload;
    }

    public void load(String url) {
        imageLoader = ImageLoaderFactoryHolder.getFactory().createLoader(this, url);
        setOnClickListener(this);
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
