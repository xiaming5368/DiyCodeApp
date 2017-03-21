package com.android.diy.app.utils;

import com.jiongbull.jlog.JLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cheng on 2017/2/24.
 */
public class TimeUtils {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",
            Locale.SIMPLIFIED_CHINESE);

    public static String getInterval(String time) {
        String result = "";
        try {
            Date now = new Date(System.currentTimeMillis());
            Date t = simpleDateFormat.parse(time);
            long diff = (now.getTime() - t.getTime()) / 1000;
            if (diff < 60) {
                result = "刚刚";
            } else if ((diff /= 60) < 60) {
                result = diff + "分钟前";
            } else if ((diff /= 60) < 24) {
                result = diff + "小时前";
            } else if ((diff /= 24) < 30) {
                result = diff + "天前";
            } else if ((diff /= 30) < 12) {
                result = diff + "月前";
            } else {
                diff /= 12;
                result = diff + "年前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime(String time) {
        // 2016-11-25T16:55:21.791+08:00
        JLog.e("time: " + time );
        return time.replace("T", " ").substring(0, 10);
    }

}
