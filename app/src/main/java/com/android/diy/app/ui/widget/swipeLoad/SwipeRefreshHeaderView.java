package com.android.diy.app.ui.widget.swipeLoad;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.android.diy.app.R;
import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by cheng on 2016/8/5.
 */
public class SwipeRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private TextView tvRefresh;
    private AVLoadingIndicatorView progressBar;
    private int mHeaderHeight;
    private boolean rotated = false;

    public SwipeRefreshHeaderView(Context context) {
        this(context, null);
    }

    public SwipeRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        progressBar = (AVLoadingIndicatorView) findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText(getResources().getString(R.string.refresh_header_refreshing_text));
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            progressBar.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText(getResources().getString(R.string.refresh_header_release_text));
                if (!rotated) {
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    rotated = false;
                }
                tvRefresh.setText(getResources().getString(R.string.refresh_header_swipe_text));
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        progressBar.setVisibility(GONE);
        tvRefresh.setText(getResources().getString(R.string.refresh_header_complete_text));
    }

    @Override
    public void onReset() {
        rotated = false;
        progressBar.setVisibility(GONE);
    }

}
