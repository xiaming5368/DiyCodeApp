package com.android.diy.app.ui.widget.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.diy.app.R;
import com.android.diy.app.ui.widget.GlideCircleTransform;
import com.bumptech.glide.Glide;

/**
 * Created by cheng on 2017/3/6.
 */
public class ImageViewAttrRoundBinding {

    @BindingAdapter("headUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .error(R.mipmap.ic_unlogin_avatar)
                .placeholder(R.mipmap.ic_unlogin_avatar)
                .transform(new GlideCircleTransform(view.getContext()))
                .into(view);
    }

}
