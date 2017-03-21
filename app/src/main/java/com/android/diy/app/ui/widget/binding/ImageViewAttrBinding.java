package com.android.diy.app.ui.widget.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.android.diy.app.R;
import com.android.diy.app.ui.widget.GlideRoundTransform;
import com.bumptech.glide.Glide;

/**
 * Created by cheng on 2017/2/22.
 */
public class ImageViewAttrBinding {

    @BindingAdapter(value = {"imageUrl", "placeHolder", "error"}, requireAll = false)
    public static void loadImage(ImageView view, String url, int placeHolderId, int errorId) {
        Glide.with(view.getContext())
                .load(url)
                .error(errorId)
                .placeholder(placeHolderId)
                .transform(new GlideRoundTransform(view.getContext(), 5))
                .into(view);
    }

}
