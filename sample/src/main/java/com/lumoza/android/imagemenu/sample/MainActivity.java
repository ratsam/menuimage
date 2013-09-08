package com.lumoza.android.imagemenu.sample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.lumoza.android.ImageMenu;
import com.lumoza.android.imagemenu.MenuItemClickListener;
import com.lumoza.android.imagemenu.aq.ImageLoaderFactoryAQImpl;
import com.lumoza.android.imagemenu.ImageLoaderFactoryHolder;

/**
 * @author Maksim Zakharov
 * @author $Author$ (current maintainer)
 * @since 1.0
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences preferences = getSharedPreferences("imagemenu", MODE_PRIVATE);
        ImageLoaderFactoryHolder.setFactory(new ImageLoaderFactoryAQImpl(preferences));

        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        final ImageMenu imageMenu2 = (ImageMenu) findViewById(R.id.image_menu_2);
        imageMenu2.load("https://www.google.ru/images/srpr/logo4w.png_");
        imageMenu2.addAction(101, "test", new MenuItemClickListener() {
            @Override
            public void onClick(ImageMenu imageMenu, int menuItemId) {

            }
        });

        final ImageMenu imageMenu3 = (ImageMenu) findViewById(R.id.image_menu_3);
        imageMenu3.load("https://www.google.ru/images/srpr/logo4w.png");
    }
}
