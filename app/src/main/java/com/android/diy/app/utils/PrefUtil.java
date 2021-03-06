package com.android.diy.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.UserDetailBean;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by cheng on 2017/3/3.
 */
public class PrefUtil {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static volatile PrefUtil prefUtil;

    private PrefUtil(Context context, String name) {
        mSharedPreferences =
                context.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static PrefUtil getInstance(Context context, String name) {
        if (prefUtil == null) {
            synchronized (PrefUtil.class) {
                if (prefUtil == null) {
                    prefUtil = new PrefUtil(context, name);
                }
            }
        }
        return prefUtil;
    }

    public SharedPreferences getSP() {
        return mSharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }

    /**
     * 存储数据(Long)
     */
    public void putLong(String key, long value) {
        mEditor.putLong(key, value).apply();
    }

    /**
     * 存储数据(Int)
     */
    public void putInt(String key, int value) {
        mEditor.putInt(key, value).apply();
    }

    /**
     * 存储数据(String)
     */
    public void putString(String key, String value) {
        mEditor.putString(key, value).apply();
    }

    /**
     * 存储数据(boolean)
     */
    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value).apply();
    }

    /**
     * 取出数据(Long)
     */
    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    /**
     * 取出数据(int)
     */
    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    /**
     * 取出数据(boolean)
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 取出数据(String)
     */
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        mEditor.clear().apply();
    }

    /**
     * 移除指定数据
     */
    public void remove(String key) {
        mEditor.remove(key).apply();
    }

    /**
     * 存储登录 Token
     */
    public static void saveToken(Context context, TokenBean token) {
        Constant.VALUE_TOKEN = token.getAccessToken();
        try {
            token.setAccessToken(
                    KeyStoreHelper.encrypt(Constant.KEYSTORE_KEY_ALIAS, token.getAccessToken()));
            token.setTokenType(KeyStoreHelper.encrypt(Constant.KEYSTORE_KEY_ALIAS, token.getTokenType()));
            token.setRefreshToken(
                    KeyStoreHelper.encrypt(Constant.KEYSTORE_KEY_ALIAS, token.getRefreshToken()));
        } catch (InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | UnrecoverableEntryException | KeyStoreException | IOException | NoSuchPaddingException | CertificateException e) {
            e.printStackTrace();
            Constant.VALUE_TOKEN = "";
            return;
        }
        PrefUtil prefUtil = PrefUtil.getInstance(context, Constant.Token.SHARED_PREFERENCES_NAME);
        prefUtil.putString(Constant.Token.ACCESS_TOKEN, token.getAccessToken());
        prefUtil.putString(Constant.Token.TOKEN_TYPE, token.getTokenType());
        prefUtil.putInt(Constant.Token.EXPIRES_IN, token.getExpiresIn());
        prefUtil.putString(Constant.Token.REFRESH_TOKEN, token.getRefreshToken());
        prefUtil.putInt(Constant.Token.CREATED_AT, token.getCreatedAt());
    }

    /**
     * 获取登录 Token
     */
    public static TokenBean getToken(Context context) {
        PrefUtil prefUtil = PrefUtil.getInstance(context, Constant.Token.SHARED_PREFERENCES_NAME);
        TokenBean token = new TokenBean();
        token.setAccessToken(prefUtil.getString(Constant.Token.ACCESS_TOKEN, ""));
        token.setTokenType(prefUtil.getString(Constant.Token.TOKEN_TYPE, ""));
        token.setExpiresIn(prefUtil.getInt(Constant.Token.EXPIRES_IN, 0));
        token.setRefreshToken(prefUtil.getString(Constant.Token.REFRESH_TOKEN, ""));
        token.setCreatedAt(prefUtil.getInt(Constant.Token.CREATED_AT, 0));
        try {
            token.setAccessToken(
                    KeyStoreHelper.decrypt(Constant.KEYSTORE_KEY_ALIAS, token.getAccessToken()));
            token.setTokenType(KeyStoreHelper.decrypt(Constant.KEYSTORE_KEY_ALIAS, token.getTokenType()));
            token.setRefreshToken(
                    KeyStoreHelper.decrypt(Constant.KEYSTORE_KEY_ALIAS, token.getRefreshToken()));
        } catch (InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | UnrecoverableEntryException | KeyStoreException | IOException | NoSuchPaddingException | CertificateException e) {
            e.printStackTrace();
        }
        Constant.VALUE_TOKEN = token.getAccessToken();
        return token;
    }

    /**
     * 存储登录信息
     */
    public static void saveMe(Context context, UserDetailBean userDetailInfo) {
        PrefUtil prefUtil = PrefUtil.getInstance(context, Constant.Token.SHARED_PREFERENCES_NAME);
        prefUtil.putString(Constant.User.LOGIN, userDetailInfo.getLogin());
        prefUtil.putString(Constant.User.AVATAR_URL, userDetailInfo.getAvatar_url());
        prefUtil.putString(Constant.User.EMAIL, userDetailInfo.getEmail());
    }

    /**
     * 获取登录信息
     */
    public static UserDetailBean getMe(Context context) {
        PrefUtil prefUtil = PrefUtil.getInstance(context, Constant.Token.SHARED_PREFERENCES_NAME);
        UserDetailBean userDetailInfo = new UserDetailBean();
        userDetailInfo.setLogin(prefUtil.getString(Constant.User.LOGIN, ""));
        userDetailInfo.setAvatar_url(prefUtil.getString(Constant.User.AVATAR_URL, ""));
        userDetailInfo.setEmail(prefUtil.getString(Constant.User.EMAIL, ""));
        return userDetailInfo;
    }

    /**
     * 清理登录信息
     */
    public static void clearMe(Context context) {
        PrefUtil prefUtil = PrefUtil.getInstance(context, Constant.Token.SHARED_PREFERENCES_NAME);
        // User
        prefUtil.putString(Constant.User.LOGIN, "");
        prefUtil.putString(Constant.User.AVATAR_URL, "");
        prefUtil.putString(Constant.User.EMAIL, "");

        // Token
        prefUtil.putString(Constant.Token.ACCESS_TOKEN, "");
        prefUtil.putString(Constant.Token.TOKEN_TYPE, "");
        prefUtil.putInt(Constant.Token.EXPIRES_IN, -1);
        prefUtil.putString(Constant.Token.REFRESH_TOKEN, "");
        prefUtil.putInt(Constant.Token.CREATED_AT, -1);
        Constant.VALUE_TOKEN = "";
    }

}
