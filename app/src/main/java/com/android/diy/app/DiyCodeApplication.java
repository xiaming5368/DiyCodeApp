package com.android.diy.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.utils.Constant;
import com.android.diy.app.utils.KeyStoreHelper;
import com.android.diy.app.utils.PrefUtil;
import com.jiongbull.jlog.JLog;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by cheng on 2017/1/19.
 */

public class DiyCodeApplication extends Application {

    public static String cacheDir;
    private static DiyCodeApplication mDiyCodeApplication;
    private String loginName;

    public static DiyCodeApplication getInstance() {
        if(null == mDiyCodeApplication) {
            mDiyCodeApplication = new DiyCodeApplication();
        }
        return mDiyCodeApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDiyCodeApplication = this;
        JLog.init(this);
        loginName = PrefUtil.getMe(this).getLogin();
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
        try {
            KeyStoreHelper.createKeys(getApplicationContext(), Constant.KEYSTORE_KEY_ALIAS);
        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

    }

    /**
     * 判断用户是否登录
     * @return
     */
    public boolean hasLogin() {
        if (!TextUtils.isEmpty(loginName)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

}
