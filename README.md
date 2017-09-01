Android登录界面设计
这是之前项目的一个界面，现在抽出来給大家看看。
##界面：

![1](http://i.imgur.com/ZwDUWBM.png)

##功能：

###（1）基本的判断，输入的是否为空

这里没有判断网络情况，实际项目中是必须要判断的

###（2）核心知识：sharePreference的使用

本地保存数据，用户名和密码，还有下面复选框的状态都要保存。
这里我自己封装了一个工具栏。
###（3）密码可见和不可见

默认密码不可见，单击图标后可见，再次点击后不可见。
点击右边的图标时，对EditText设置代码即可：

```
  if (iv_see_password.isSelected()) {
            iv_see_password.setSelected(false);
            //密码不可见
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            iv_see_password.setSelected(true);
            //密码可见
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

```
###（4）登录后会显示链接服务器的对话框
这里有一个自定义的对话框，也是可以自己修改对话框的样式和显示的文字

###（5）这里密码保存到本地经过了base64的加密
这里创建了一个加密工具类，base64加密过的字符串，会很容易就被解密出来。
但是我方向一个字符串可以加密N次，解密也是要N次，就可以得到原来的字符串。
所以我在工具类里面设置了N的数值，别人就很难知道我的base64怎么解密了！
值得注意的是：多次解密过程有可能会給字符串添加空格，所有每次要对字符串去空格！
加密解密的次数太大，也是要耗时间的，不建议N的数值大于20.

##动态效果：

![2](http://i.imgur.com/WgtPUBz.gif)

如果勾选了自动登录，下次进入登录界面，会自动请求服务器登录。如下图所示：

![3](http://i.imgur.com/4KZBIZy.gif)


这里分享一下主要的代码：

##登录页面的xml布局代码：
```

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#fff"
        android:orientation="vertical">

    <TextView
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:text="登录界面"
            android:gravity="center"
            android:textSize="30sp"
            />
    <View android:layout_width="match_parent" android:layout_height="@dimen/dp_1"
          android:background="@color/orange_main"
            />

    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/item_height_normal"
            android:layout_marginLeft="@dimen/margin_large"
            android:layout_marginRight="@dimen/margin_large"
            android:layout_marginTop="@dimen/dp_120">

        <ImageView
                android:id="@+id/img_account"
                android:layout_width="@dimen/dp_19"
                android:layout_height="@dimen/dp_20"
                android:layout_alignParentBottom="true"
                android:layout_marginBottom="@dimen/margin_tiny"
                android:layout_marginLeft="@dimen/margin_tiny"
                android:scaleType="fitXY"
                android:src="@drawable/icon_login_account"/>

        <EditText
                android:id="@+id/et_account"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_gravity="center"
                android:layout_marginBottom="@dimen/margin_tiny"
                android:layout_marginLeft="@dimen/margin_normal"
                android:layout_toRightOf="@+id/img_account"
                android:background="@null"
                android:hint="@string/account"
                android:maxLines="1"
                android:textColor="@android:color/black"
                android:textColorHint="@color/tv_gray_deep"
                android:textSize="@dimen/text_size_normal"/>

        <View
                android:layout_width="match_parent"
                android:layout_height="@dimen/line_height"
                android:layout_alignParentBottom="true"
                android:layout_marginLeft="@dimen/margin_normal"
                android:layout_toRightOf="@+id/img_account"
                android:background="@color/orange_light"/>
    </RelativeLayout>

    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/item_height_normal"
            android:layout_marginLeft="@dimen/margin_large"
            android:layout_marginRight="@dimen/margin_large">

        <ImageView
                android:id="@+id/img_pw"
                android:layout_width="@dimen/dp_18"
                android:layout_height="@dimen/dp_20"
                android:layout_alignParentBottom="true"
                android:layout_marginBottom="@dimen/margin_tiny"
                android:layout_marginLeft="@dimen/margin_tiny"
                android:scaleType="fitXY"
                android:src="@drawable/icon_login_pw"/>

        <EditText
                android:id="@+id/et_password"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_gravity="center"
                android:layout_marginBottom="@dimen/margin_tiny"
                android:layout_marginLeft="@dimen/margin_normal"
                android:layout_toRightOf="@+id/img_pw"
                android:background="@null"
                android:hint="@string/password"
                android:inputType="textPassword"
                android:maxLines="1"
                android:textColor="@android:color/black"
                android:textColorHint="@color/tv_gray_deep"
                android:textSize="@dimen/text_size_normal"/>
        <ImageView
                android:id="@+id/iv_see_password"
                android:layout_width="@dimen/image_height_litter"
                android:layout_height="@dimen/image_height_litter"
                android:src="@drawable/image_password_bg"
                android:layout_centerVertical="true"
                android:layout_alignParentRight="true"
                android:scaleType="fitXY"
                />
        <View
                android:layout_width="match_parent"
                android:layout_height="@dimen/line_height"
                android:layout_alignParentBottom="true"
                android:layout_marginLeft="@dimen/margin_normal"
                android:layout_toRightOf="@+id/img_pw"
                android:background="@color/orange_light"/>
    </RelativeLayout>
    <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="@dimen/margin_large"
            android:layout_marginRight="@dimen/margin_large"
            android:layout_marginTop="@dimen/margin_small"
            android:paddingBottom="@dimen/margin_small"
            android:paddingTop="@dimen/margin_small"
            android:orientation="horizontal"
            android:gravity="center"
            >
        <CheckBox
                android:id="@+id/checkBox_password"
                android:padding="@dimen/dp_10"
                android:textSize="@dimen/text_size_normal"
                android:layout_gravity="center"
                android:layout_width="wrap_content"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:text="@string/check_password"
                android:textColor="@color/top_bar_normal_bg" android:checked="false"/>
        <CheckBox
                android:id="@+id/checkBox_login"
                android:padding="@dimen/dp_10"
                android:textSize="@dimen/text_size_normal"
                android:layout_gravity="center"
                android:layout_width="wrap_content"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:text="@string/check_login"
                android:textColor="@color/top_bar_normal_bg" android:checked="false"/>

    </LinearLayout>
    <Button
            android:id="@+id/btn_login"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="@dimen/margin_large"
            android:layout_marginRight="@dimen/margin_large"
            android:layout_marginTop="@dimen/margin_huge"
            android:paddingBottom="@dimen/margin_small"
            android:paddingTop="@dimen/margin_small"
            android:text="@string/login"
            android:background="@drawable/btn_orange_selector"
            android:textColor="@android:color/white"
            android:textSize="@dimen/text_size_normal"/>


</LinearLayout>

```


##sharePreference工具类代码：
```
package com.lwz.login_demo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 这是一个SharePreference的根据类，使用它可以更方便的数据进行简单存储
 * 这里只要知道基本调用方法就可以了
 * 1.通过构造方法来传入上下文和文件名
 * 2.通过putValue方法传入一个或多个自定义的ContentValue对象，进行数据存储
 * 3.通过get方法来获取数据
 * 4.通过clear方法来清除这个文件的数据
 * 这里没有提供清除单个key的数据，是因为存入相同的数据会自动覆盖，没有必要去理会
 */
public class SharedPreferencesUtils {
    //定义一个SharePreference对象
    SharedPreferences sharedPreferences;
    //定义一个上下文对象

    //创建SharePreference对象时要上下文和存储的模式
    //通过构造方法传入一个上下文
    public SharedPreferencesUtils(Context context, String fileName) {
        //实例化SharePreference对象，使用的是get方法，而不是new创建
        //第一个参数是文件的名字
        //第二个参数是存储的模式，一般都是使用私有方式：Context.MODE_PRIVATE
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * 存储数据
     * 这里要对存储的数据进行判断在存储
     * 只能存储简单的几种数据
     * 这里使用的是自定义的ContentValue类，来进行对多个数据的处理
     */
    //创建一个内部类使用，里面有key和value这两个值
    public static class ContentValue {
        String key;
        Object value;

        //通过构造方法来传入key和value
        public ContentValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    //一次可以传入多个ContentValue对象的值
    public void putValues(ContentValue... contentValues) {
        //获取SharePreference对象的编辑对象，才能进行数据的存储
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //数据分类和存储
        for (ContentValue contentValue : contentValues) {
            //如果是字符型类型
            if (contentValue.value instanceof String) {
                editor.putString(contentValue.key, contentValue.value.toString()).commit();
            }
            //如果是int类型
            if (contentValue.value instanceof Integer) {
                editor.putInt(contentValue.key, Integer.parseInt(contentValue.value.toString())).commit();
            }
            //如果是Long类型
            if (contentValue.value instanceof Long) {
                editor.putLong(contentValue.key, Long.parseLong(contentValue.value.toString())).commit();
            }
            //如果是布尔类型
            if (contentValue.value instanceof Boolean) {
                editor.putBoolean(contentValue.key, Boolean.parseBoolean(contentValue.value.toString())).commit();
            }

        }
    }


    //获取数据的方法
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key, Boolean b) {
        return sharedPreferences.getBoolean(key, b);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, -1);
    }

    //清除当前文件的所有的数据
    public void clear() {
        sharedPreferences.edit().clear().commit();
    }

}


```


##登录页面的java逻辑代码：
```
package com.lwz.login_demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import com.lwz.login_demo.R;
import com.lwz.login_demo.util.Base64Utils;
import com.lwz.login_demo.util.SharedPreferencesUtils;
import com.lwz.login_demo.widget.LoadingDialog;

/**
 * 登录界面
 */

public class LoginActivity extends Activity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    //布局内的控件
    private EditText et_name;
    private EditText et_password;
    private Button mLoginBtn;
    private CheckBox checkBox_password;
    private CheckBox checkBox_login;
    private ImageView iv_see_password;

    private LoadingDialog mLoadingDialog; //显示正在加载的对话框


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setupEvents();
        initData();

    }

    private void initData() {


        //判断用户第一次登陆
        if (firstLogin()) {
            checkBox_password.setChecked(false);//取消记住密码的复选框
            checkBox_login.setChecked(false);//取消自动登录的复选框
        }
        //判断是否记住密码
        if (remenberPassword()) {
            checkBox_password.setChecked(true);//勾选记住密码
            setTextNameAndPassword();//把密码和账号输入到输入框中
        } else {
            setTextName();//把用户账号放到输入账号的输入框中
        }

        //判断是否自动登录
        if (autoLogin()) {
            checkBox_login.setChecked(true);
            login();//去登录就可以

        }
    }

    /**
     * 把本地保存的数据设置数据到输入框中
     */
    public void setTextNameAndPassword() {
        et_name.setText("" + getLocalName());
        et_password.setText("" + getLocalPassword());
    }

    /**
     * 设置数据到输入框中
     */
    public void setTextName() {
        et_name.setText("" + getLocalName());
    }


    /**
     * 获得保存在本地的用户名
     */
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String name = helper.getString("name");
        return name;
    }


    /**
     * 获得保存在本地的密码
     */
    public String getLocalPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        String password = helper.getString("password");
        return Base64Utils.decryptBASE64(password);   //解码一下
//       return password;   //解码一下

    }

    /**
     * 判断是否自动登录
     */
    private boolean autoLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean autoLogin = helper.getBoolean("autoLogin", false);
        return autoLogin;
    }

    /**
     * 判断是否记住密码
     */
    private boolean remenberPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean remenberPassword = helper.getBoolean("remenberPassword", false);
        return remenberPassword;
    }


    private void initViews() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        et_name = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        checkBox_password = (CheckBox) findViewById(R.id.checkBox_password);
        checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);
        iv_see_password = (ImageView) findViewById(R.id.iv_see_password);
    }

    private void setupEvents() {
        mLoginBtn.setOnClickListener(this);
        checkBox_password.setOnCheckedChangeListener(this);
        checkBox_login.setOnCheckedChangeListener(this);
        iv_see_password.setOnClickListener(this);

    }

    /**
     * 判断是否是第一次登陆
     */
    private boolean firstLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
        boolean first = helper.getBoolean("first", true);
        if (first) {
            //创建一个ContentVa对象（自定义的）设置不是第一次登录，,并创建记住密码和自动登录是默认不选，创建账号和密码为空
            helper.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""));
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loadUserName();    //无论如何保存一下用户名
                login(); //登陆
                break;
            case R.id.iv_see_password:
                setPasswordVisibility();    //改变图片并设置输入框的文本可见或不可见
                break;

        }
    }

    /**
     * 模拟登录情况
     * 用户名csdn，密码123456，就能登录成功，否则登录失败
     */
    private void login() {

        //先做一些基本的判断，比如输入的用户命为空，密码为空，网络不可用多大情况，都不需要去链接服务器了，而是直接返回提示错误
      if (getAccount().isEmpty()){
          showToast("你输入的账号为空！");
          return;
      }

        if (getPassword().isEmpty()){
            showToast("你输入的密码为空！");
            return;
        }
        //登录一般都是请求服务器来判断密码是否正确，要请求网络，要子线程
        showLoading();//显示加载框
        Thread loginRunnable = new Thread() {

            @Override
            public void run() {
                super.run();
                setLoginBtnClickable(false);//点击登录后，设置登录按钮不可点击状态


                //睡眠3秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //判断账号和密码
                if (getAccount().equals("csdn") && getPassword().equals("123456")) {
                    showToast("登录成功");
                    loadCheckBoxState();//记录下当前用户记住密码和自动登录的状态;

                    startActivity(new Intent(LoginActivity.this, LoginAfterActivity.class));
                    finish();//关闭页面
                } else {
                    showToast("输入的登录账号或密码不正确");
                }

                setLoginBtnClickable(true);  //这里解放登录按钮，设置为可以点击
                hideLoading();//隐藏加载框
            }
        };
        loginRunnable.start();


    }


    /**
     * 保存用户账号
     */
    public void loadUserName() {
        if (!getAccount().equals("") || !getAccount().equals("请输入登录账号")) {
            SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");
            helper.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }

    }

    /**
     * 设置密码可见和不可见的相互转换
     */
    private void setPasswordVisibility() {
        if (iv_see_password.isSelected()) {
            iv_see_password.setSelected(false);
            //密码不可见
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        } else {
            iv_see_password.setSelected(true);
            //密码可见
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

    }

    /**
     * 获取账号
     */
    public String getAccount() {
        return et_name.getText().toString().trim();//去掉空格
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return et_password.getText().toString().trim();//去掉空格
    }


    /**
     * 保存用户选择“记住密码”和“自动登陆”的状态
     */
    private void loadCheckBoxState() {
        loadCheckBoxState(checkBox_password, checkBox_login);
    }

    /**
     * 保存按钮的状态值
     */
    public void loadCheckBoxState(CheckBox checkBox_password, CheckBox checkBox_login) {

        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(this, "setting");

        //如果设置自动登录
        if (checkBox_login.isChecked()) {
            //创建记住密码和自动登录是都选择,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", true),
                    new SharedPreferencesUtils.ContentValue("password", Base64Utils.encryptBASE64(getPassword())));

        } else if (!checkBox_password.isChecked()) { //如果没有保存密码，那么自动登录也是不选的
            //创建记住密码和自动登录是默认不选,密码为空
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", ""));
        } else if (checkBox_password.isChecked()) {   //如果保存密码，没有自动登录
            //创建记住密码为选中和自动登录是默认不选,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("password", Base64Utils.encryptBASE64(getPassword())));
        }
    }

    /**
     * 是否可以点击登录按钮
     *
     * @param clickable
     */
    public void setLoginBtnClickable(boolean clickable) {
        mLoginBtn.setClickable(clickable);
    }


    /**
     * 显示加载的进度款
     */
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this, getString(R.string.loading), false);
        }
        mLoadingDialog.show();
    }


    /**
     * 隐藏加载的进度框
     */
    public void hideLoading() {
        if (mLoadingDialog != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog.hide();
                }
            });

        }
    }


    /**
     * CheckBox点击时的回调方法 ,不管是勾选还是取消勾选都会得到回调
     *
     * @param buttonView 按钮对象
     * @param isChecked  按钮的状态
     */
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == checkBox_password) {  //记住密码选框发生改变时
            if (!isChecked) {   //如果取消“记住密码”，那么同样取消自动登陆
                checkBox_login.setChecked(false);
            }
        } else if (buttonView == checkBox_login) {   //自动登陆选框发生改变时
            if (isChecked) {   //如果选择“自动登录”，那么同样选中“记住密码”
                checkBox_password.setChecked(true);
            }
        }
    }


    /**
     * 监听回退键
     */
    @Override
    public void onBackPressed() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.cancel();
            } else {
                finish();
            }
        } else {
            finish();
        }

    }

    /**
     * 页面销毁前回调的方法
     */
    protected void onDestroy() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
        super.onDestroy();
    }


    public void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}



```

你也可以参考我的项目的代码：https://github.com/liwenzhi/LoginDemo.git
上面只是一个简单的示例，逻辑相对会有点啰嗦，因为这是我一个MVP框架程序中抠下来的代码。
请求服务器也是创建子线程来模拟实现的。登录页面可以根据实际需求再修改修改。

共勉：
