package com.android.diy.app.utils;

import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cheng on 2017/2/28.
 */
public class UrlUtil {

    /**
     * 获取 URL 的 Host
     */
    public static String getHosts(String url) {
        if (url == null || url.trim().equals("")) {
            return "";
        }
        String host = "";
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            host = matcher.group();
        }
        return host;
    }

    /**
     * 获取 URL 的 Host
     * TODO: Java自带的获取主机名的方法, 测试中发现会抛异常
     */
    public static String getHost(String urlString) {
        String result = urlString;
        try {
            URL url = new URL(urlString);
            result = url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 提取文本中的链接
     */
    public static String getUrl(String text) {
        Pattern p = Patterns.WEB_URL;
        Matcher matcher = p.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
