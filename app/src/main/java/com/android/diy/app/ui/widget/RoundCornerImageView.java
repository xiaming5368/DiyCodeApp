package com.android.diy.app.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cheng on 2017/2/28.
 */
public class RoundCornerImageView extends ImageView {

    private Paint paint = new Paint();
    private Bitmap bitmap;
    private BitmapShader shader;
    private RectF rectF;
    private float radius;

    public RoundCornerImageView(Context context) {
        this(context, null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        radius = getResources().getDisplayMetrics().density * 8f + 0.5f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (rectF == null) {
            rectF = new RectF(radius, radius, getMeasuredWidth() - radius, getMeasuredHeight() - radius);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            rectF = new RectF(radius, radius, w - radius, h - radius);
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm != null) {
            bitmap = bm;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            bitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), true);
            shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            canvas.drawRoundRect(rectF, radius, radius, paint);
        }
    }
}