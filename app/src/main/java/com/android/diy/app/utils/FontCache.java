package com.android.diy.app.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.ArrayMap;

/**
 * Created by cheng on 2017/2/21.
 */
public class FontCache {

    private static ArrayMap<String, Typeface> fontCache = new ArrayMap<String, Typeface>();

    public static Typeface getTypeface(String fontName, Context context) {
        Typeface tf = fontCache.get(fontName);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), fontName);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(fontName, tf);
        }
        return tf;
    }

}
