package com.lumoza.android.imagemenu;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public class MenuFactoryHolder {

    private static volatile MenuFactory factory;

    public static MenuFactory getFactory() {
        return factory;
    }

    public static void setFactory(MenuFactory factory) {
        MenuFactoryHolder.factory = factory;
    }

    /**
     * Private constructor for 'static' class.
     */
    private MenuFactoryHolder() {
    }
}
