package com.lumoza.android.imagemenu;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public interface ImageLoader {

    /**
     * Reload image if previous load failed.
     * Force 'online' mode.
     */
    void reload();

    /**
     * Get image load status.
     *
     * @return current status
     */
    LoadStatus getStatus();
}
