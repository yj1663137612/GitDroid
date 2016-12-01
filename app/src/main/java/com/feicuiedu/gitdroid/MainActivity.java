package com.feicuiedu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.HotRepoFragment;
import com.feicuiedu.gitdroid.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private Button mBtnLogin;
    private ImageView mIvIcon;
    private ActivityUtils mActivityUtils;
    private FragmentManager mFragmentManager;
    private HotRepoFragment mHotRepoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置当前视图(更改了当前视图内容,将导致onContentChanged方法触发)
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        //获取V4包的Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        /**
         * 需要处理的视图
         * 1. toolbar
         * 2. DrawerLayout
         * 3. NavigationView
         */
        //设置ActionBar
        setSupportActionBar(mToolbar);
        // 设置监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        //设置DrawerLayout的侧滑监听
        mDrawerLayout.addDrawerListener(toggle);
        //给NavigationView实现监听事件
        mNavigationView.setNavigationItemSelectedListener(this);
        mBtnLogin = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.btnLogin);
        mIvIcon = ButterKnife.findById(mNavigationView.getHeaderView(0), R.id.ivIcon);
        //给登录按钮设置监听事件
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/12/1 点击跳转到登录界面
                mActivityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
        mHotRepoFragment = new HotRepoFragment();
        replaceFragment(mHotRepoFragment);
    }

    /**
     * 1.创建Fragment
     * 2.提供一个方法，根据传入的Fragment对象切换Fragment
     * 3.展示：
     */
    private void replaceFragment(Fragment fragment) {

        //获取业务对象
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //执行业务
        fragmentTransaction.replace(R.id.container, fragment);
        //提交业务
        fragmentTransaction.commit();
    }

    //主要做了我们基本登录信息的改变
    @Override
    protected void onStart() {
        // TODO: 2016/12/1 改变登录信息
        super.onStart();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //如果条目被点击，设为未被点击
        if (item.isChecked()) {
            item.setChecked(false);
        }
        // TODO: 2016/12/1  切换视图
        switch (item.getItemId()) {
            //最热门
            case R.id.github_hot_repo:
                if (mHotRepoFragment.isAdded()) {
                    replaceFragment(mHotRepoFragment);
                }
                break;
            //开发者
            case R.id.github_hot_coder:
                break;
            //流行趋势
            case R.id.github_trend:
                break;
        }
        //关闭当前DrawerLayout   GravityCompat.START：表示左边的DrawerLayout
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
