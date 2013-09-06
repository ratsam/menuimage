package com.lumoza.android.imagemenu;

/**
 * @author Maksim Zakharov
 * @since 1.0
 */
public enum LoadStatus {

    /**
     * Url provided, load in progress
     */
    LOADING,
    /**
     * File loaded and displayed
     */
    LOADED,
    /**
     * Error occurred during file download.
     */
    ERROR
}
