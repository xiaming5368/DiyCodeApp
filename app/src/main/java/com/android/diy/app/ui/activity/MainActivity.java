package com.android.diy.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.databinding.ActivityMainBinding;
import com.android.diy.app.presenter.LoginPresenter;
import com.android.diy.app.ui.adapter.MainPagerAdapter;
import com.android.diy.app.ui.widget.GlideCircleTransform;
import com.android.diy.app.utils.Constant;
import com.android.diy.app.utils.PrefUtil;
import com.android.diy.app.utils.RxBus;
import com.bumptech.glide.Glide;
import com.jiongbull.jlog.JLog;

/**
 * Created by cheng on 2017/1/19.
 * 首页
 */
public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TOPIC_BEAN = "topic_bean";
    private ImageView imgHeader;
    private TextView tvLogin, tvEmail;
    private Intent intent;
    private LoginPresenter mPresenter;
    PagerAdapter pagerAdapter;

    protected boolean mSearchCheck;
    private SearchView mSearchView;
    private MenuItem mMenuItem;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mDataBinding.appBar.toolbar.setLogo(R.mipmap.logo_actionbar);
        mDataBinding.appBar.toolbar.setTitle("");
        setSupportActionBar(mDataBinding.appBar.toolbar);

        mDataBinding.appBar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, ReleaseTopicActivity.class),
                        ReleaseTopicActivity.REQUEST_CODE);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDataBinding.drawerLayout, mDataBinding.appBar.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDataBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mDataBinding.navView.setNavigationItemSelectedListener(this);
        View headerView = mDataBinding.navView.getHeaderView(0);
        imgHeader = (ImageView) headerView.findViewById(R.id.image_header_user);
        tvLogin = (TextView) headerView.findViewById(R.id.text_login);
        tvEmail = (TextView) headerView.findViewById(R.id.text_email);
        tvLogin.setText(getString(R.string.please_login_text));
        tvEmail.setText("");

        TokenBean tokenBean = PrefUtil.getToken(this);
        if (!TextUtils.isEmpty(tokenBean.getAccessToken())) {
            UserDetailBean userDetailBean = PrefUtil.getMe(MainActivity.this);
            if (!TextUtils.isEmpty(userDetailBean.getLogin())
                    && !TextUtils.isEmpty(userDetailBean.getAvatar_url())
                    && !TextUtils.isEmpty(userDetailBean.getEmail())) {
                showMe(userDetailBean);
            } else {
                JLog.e("user in null");
                mPresenter = new LoginPresenter();
                mPresenter.getMe();
            }
        } else {
            JLog.e("token is null");
            mPresenter = new LoginPresenter();
        }

        imgHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(Constant.VALUE_TOKEN)) {
                    intent = new Intent(MainActivity.this, MineActivity.class);
                    intent.putExtra(MineActivity.LOGIN_NAME, tvLogin.getText().toString().trim());
                    startActivity(intent);
                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, LoginActivity.REQUEST_CODE);
                }
                mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mDataBinding.appBar.appContent.viewPager.setAdapter(pagerAdapter);
        mDataBinding.appBar.appContent.viewPager.setOffscreenPageLimit(3);
        mDataBinding.appBar.tabLayout.setupWithViewPager(mDataBinding.appBar.appContent.viewPager);

    }

    private boolean checkLogin() {
        UserDetailBean userDetailBean = PrefUtil.getMe(MainActivity.this);
        if(userDetailBean != null) {
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LoginActivity.REQUEST_CODE:
                    if(checkLogin()) {
                        showMe(PrefUtil.getMe(MainActivity.this));
                    } else {
                        tvLogin.setText(getString(R.string.please_login_text));
                        tvEmail.setText("");
                        imgHeader.setImageResource(R.mipmap.ic_unlogin_avatar);
                    }
                    break;
                case ReleaseTopicActivity.REQUEST_CODE:
                    TopicDetailBean topicDetailBean = data.getExtras().getParcelable(TOPIC_BEAN);
                    RxBus.getInstance().post(topicDetailBean);
                    break;
                case SettingActivity.REQUEST_CODE:
                    tvLogin.setText(getString(R.string.please_login_text));
                    tvEmail.setText("");
                    imgHeader.setImageResource(R.mipmap.ic_unlogin_avatar);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showMe(UserDetailBean userDetailBean) {
        tvLogin.setText(userDetailBean.getLogin());
        tvEmail.setText(userDetailBean.getEmail());
        Glide.with(MainActivity.this)
                .load(userDetailBean.getAvatar_url())
                .crossFade()
                .bitmapTransform(new GlideCircleTransform(MainActivity.this))
                .error(R.mipmap.ic_unlogin_avatar)
                .into(imgHeader);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mMenuItem = menu.findItem(R.id.action_search);
        mMenuItem.setVisible(true);

        mSearchView = (SearchView) mMenuItem.getActionView();
        mSearchView.setQueryHint(getString(R.string.search));
        EditText etContent = (EditText) mSearchView.findViewById(R.id.search_src_text);
        etContent.setHintTextColor(ContextCompat.getColor(this, R.color.text_gray));
        mSearchView.setOnQueryTextListener(onQuerySearchView);
        mSearchView.setOnCloseListener(mOnCloseListener);

        mSearchCheck = false;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_topic) {  // 我的帖子
            if(checkLogin()) {
                startActivity(new Intent(MainActivity.this, MyTopicActivity.class)
                        .putExtra(MyTopicActivity.TYPE, MyTopicActivity.TYPE_CREATE));
            } else {
                showShortToast(getString(R.string.please_login_text));
            }
        } else if (id == R.id.nav_collection) { // 我的收藏
            startActivity(new Intent(MainActivity.this, MyTopicActivity.class)
                    .putExtra(MyTopicActivity.TYPE, MyTopicActivity.TYPE_FAVORITE));
        } else if (id == R.id.nav_comment) { // 我的评论

        } else if (id == R.id.nav_about) { // 关于我们
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_setting) { // 软件设置
            startActivityForResult(new Intent(MainActivity.this, SettingActivity.class),
                    SettingActivity.REQUEST_CODE);
        }
        mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String keyWord) {
            startActivity(new Intent(MainActivity.this,
                    SearchActivity.class).putExtra(SearchActivity.KEY_WORD, keyWord));
            mMenuItem.collapseActionView();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String keyWord) {
            if (mSearchCheck){

            }
            return false;
        }
    };

    private SearchView.OnCloseListener mOnCloseListener = new SearchView.OnCloseListener() {

        @Override
        public boolean onClose() {
            return mSearchCheck;
        }
    };

}
