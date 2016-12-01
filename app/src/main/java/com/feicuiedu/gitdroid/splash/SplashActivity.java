package com.feicuiedu.gitdroid.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feicuiedu.gitdroid.MainActivity;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnLogin) void login() {
        activityUtils.startActivity(LoginActivity.class);
        finish();
    }

    @OnClick(R.id.btnEnter) void enter() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }
}
