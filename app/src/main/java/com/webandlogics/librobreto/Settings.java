package com.webandlogics.librobreto;

/**
 * Created by alvaro on 11/09/16.
 * URL's, keys and that kind of stuff
 */
public class Settings {
    public static final String API_BASE_URL = "https://www.goodreads.com";
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String API_SECRET = BuildConfig.API_SECRET;

    // This MUST be the same as in data param in login activity in manifest file
    public static final String OAUTH_REDIRECT_URI = "librobreto://librobreto/login";

    // Logging
    final static public String TAG = "Librobreto";
}
