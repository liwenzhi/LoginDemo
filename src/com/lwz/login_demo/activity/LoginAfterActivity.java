package com.lwz.login_demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.lwz.login_demo.R;
import com.lwz.login_demo.util.SharedPreferencesUtils;

/**
 * 登录成功后的界面
 */
public class LoginAfterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_after);
    }

    /**
     * 返回登录界面，不消除用户和密码
     */
    public void toLogin(View view) {
        //不用自动登录就可
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        //创建记住密码和自动登录是默认不选,密码为空
        helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", false));
        startActivity(new Intent(this, LoginActivity.class));

    }

    /**
     * 返回登录界面，消除用密码
     */
    public void toLogin2(View view) {
        //置空密码即可
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        //创建记住密码和自动登录是默认不选,密码为空
        helper.putValues(
                new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                new SharedPreferencesUtils.ContentValue("autoLogin", false),
                new SharedPreferencesUtils.ContentValue("password", ""));
        startActivity(new Intent(this, LoginActivity.class));
    }
}
