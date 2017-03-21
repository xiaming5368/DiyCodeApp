package com.android.diy.app.utils;

/**
 * Created by cheng on 2017/2/17.
 */
public class Constant {

    public static final String KEYSTORE_KEY_ALIAS = "DiyCodeApp";
    public static final String VALUE_CLIENT_ID = "7e61037c";
    public static final String VALUE_CLIENT_SECRET =
            "db779d8fcfaf92d80d888ea9566404b55058a05c5aba7cf47cd49c9c182afd65";
    public static final String VALUE_GRANT_TYPE = "password";
    public static final String KEY_TOKEN = "Authorization";
    public static final String VALUE_TOKEN_PREFIX = "Bearer ";
    public static String VALUE_TOKEN = "";

    public static class Token {
        public static final String SHARED_PREFERENCES_NAME = "sign";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String TOKEN_TYPE = "token_type";
        public static final String EXPIRES_IN = "expires_in";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String CREATED_AT = "created_at";
    }

    public static class User {
        public static final String LOGIN = "login";
        public static final String AVATAR_URL = "avatar_url";
        public static final String EMAIL = "email";
    }


}
