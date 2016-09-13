package com.webandlogics.librobreto;

/**
 * Created by alvaro on 11/09/16.
 * URL's, keys and that kind of stuff
 */
public class Settings {
    final public static String API_BASE_URL = "https://www.goodreads.com";
    final public static String API_KEY = BuildConfig.API_KEY;
    final public static String API_SECRET = BuildConfig.API_SECRET;

    // OAUTH
    final public static String REQUEST_TOKEN = "request_token";
    final public static String REQUEST_TOKEN_SECRET = "request_token_secret";
    final public static String OAUTH_TOKEN = "oauth_token";
    // This one MUST be the same as in data param in login activity in manifest file
    final public static String OAUTH_REDIRECT_URI = "librobreto://librobreto/login";


    // Logging
    final static public String TAG = "Librobreto";

    //Preferences
    final static public String SHARED_PREFERENCES = "com.webandlogics.librobreto.preferences";
}
