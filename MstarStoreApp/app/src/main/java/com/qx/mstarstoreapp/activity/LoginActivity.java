package com.qx.mstarstoreapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qx.mstarstoreapp.R;
import com.qx.mstarstoreapp.base.AppURL;
import com.qx.mstarstoreapp.base.BaseActivity;
import com.qx.mstarstoreapp.base.BaseApplication;
import com.qx.mstarstoreapp.net.OKHttpRequestUtils;
import com.qx.mstarstoreapp.net.VolleyRequestUtils;
import com.qx.mstarstoreapp.utils.L;
import com.qx.mstarstoreapp.utils.SpUtils;
import com.qx.mstarstoreapp.utils.StringUtils;
import com.qx.mstarstoreapp.utils.ToastManager;
import com.qx.mstarstoreapp.viewutils.CountTimerButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/7.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.id_btn_register)
    Button idBtnRegister;
    @Bind(R.id.id_ed_name)
    EditText idEdName;
    @Bind(R.id.id_ed_password)
    EditText idEdPassword;
    @Bind(R.id.id_ed_code)
    EditText idEdCode;
    String name, pwd, phone, code;
    CountTimerButton mCountDownTimerUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);
        ButterKnife.bind(this);
        getBackIntent();
        String token=BaseApplication.spUtils.getString(SpUtils.key_tokenKey);
        if(!StringUtils.isEmpty(token)){
            BaseApplication.setToken(token);
            openActivity(MainActivity.class, null);
            finish();
            return;
        }
        name=BaseApplication.spUtils.getString(SpUtils.key_username);
        pwd=BaseApplication.spUtils.getString(SpUtils.key_password);
        if(!StringUtils.isEmpty(name)){
            idEdName.setText(name);
        } if(!StringUtils.isEmpty(pwd)){
            idEdPassword.setText(pwd);
        }


        idBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNetData();
            }
        });

        Button  textView= (Button) findViewById(R.id.tv_get_auth_code);
        mCountDownTimerUtils = new CountTimerButton(textView, 60000, 1000);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimerUtils.start();
                getNetCode();
            }
        });

    }

    /*得到没登陆前的实例*/
    public void getBackIntent() {
        Intent callingIntent = getIntent();
        if (callingIntent != null && callingIntent.getExtras() != null) {
            nextActivity = (Class<?>) callingIntent.getExtras().get(JUMP_TO_ACTIVITY);
        }
    }


    @Override
    public void loadNetData() {
        name = idEdName.getText().toString();
        pwd = idEdPassword.getText().toString();
        code = idEdCode.getText().toString();
        if (StringUtils.isEmpty(name)) {
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            return;
        }
        if (StringUtils.isEmpty(code)) {
            return;
        }
        baseShowWatLoading();
        // 进行登录请求
        String lgUrl = AppURL.URL_LOGIN + "userName=" + name + "&password=" + pwd + "&phoneCode=" + code;
        L.e("netLogin" + lgUrl);
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("OKHttpRequestUtils" + result);
                baseHideWatLoading();
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                    String token = new Gson().fromJson(result, JsonObject.class).getAsJsonObject("data").get("tokenKey").getAsString();
                    L.e("成功" + token);
                    BaseApplication.spUtils.saveString(SpUtils.key_tokenKey,token);
                    BaseApplication.spUtils.saveString(SpUtils.key_username,name);
                    BaseApplication.spUtils.saveString(SpUtils.key_password,pwd);
                    BaseApplication.setToken(token);
                    openActivity(MainActivity.class, null);
                    finish();
                    return;
                    //loginSuccess();
                }
                if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    ToastManager.showToastReal(message);
                    L.e(message);
                }
                if (error == 2) {
                    L.e("重新登录");
                   // netLogin();
                }
                if (error == 3) {
                    L.e("未审核");
                }
            }

            @Override
            public void onFail(String fail) {

            }


        });
//        OKHttpRequestUtils.getmInstance().requestData(lgUrl, this, new OKHttpRequestUtils.OKHttpFinallCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                L.e("OKHttpRequestUtils" + result);
//                baseHideWatLoading();
//                String token = new Gson().fromJson(result, JsonObject.class).getAsJsonObject("data").get("tokenKey").getAsString();
//                L.e("成功" + token);
//                BaseApplication.spUtils.saveString(SpUtils.key_tokenKey,token);
//                BaseApplication.setToken(token);
//                openActivity(MainActivity.class, null);
//                return;
//                //loginSuccess();
//            }
//
//            @Override
//            public void onReLogin() {
//                baseHideWatLoading();
//                L.e("重新登录");
//            }
//
//            @Override
//            public void onErrorMsg(String msg) {
//                baseHideWatLoading();
//                L.e(msg);
//            }
//        });
    }


    public void loginSuccess() {
        if (nextActivity != null) {
            final Intent intent = new Intent(LoginActivity.this, nextActivity);
            //intent.putExtra(GET_TO, "");
            (new android.os.Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, 1000);
            return;
        }
        openActivity(MainActivity.class, null);
        finish();
//        Intent intent = new Intent();
//        intent.putExtra("fragmentid", getIntent().getIntExtra("fragmentid", -1));
//        setResult(94, intent);
        //
    }

    public void getNetCode(){
        name = idEdName.getText().toString();
        pwd = idEdPassword.getText().toString();
        if (StringUtils.isEmpty(name)) {
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            return;
        }
        baseShowWatLoading();
        // 进行登录请求
        String lgUrl = AppURL.URL_LOGING_CODE + "userName=" + name + "&password=" + pwd;
        VolleyRequestUtils.getInstance().getCookieRequest(this, lgUrl, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("OKHttpRequestUtils" + result);
                baseHideWatLoading();
                int error = OKHttpRequestUtils.getmInstance().getResultCode(result);
                if (error == 0) {
                }
                if (error == 1) {
                    String message = new Gson().fromJson(result, JsonObject.class).get("message").getAsString();
                    L.e(message);
                    ToastManager.showToastReal(message);
                }
                if (error == 2) {
                    L.e("重新登录");
                }
                if (error == 3) {
                    L.e("未审核");
                }
            }
            @Override
            public void onFail(String fail) {

            }
        });
    }

    public void onRegister(View v) {
        openActivity(RegisterActivity.class, null);
    }

    public void onUpdatePassword(View v) {
        openActivity(FrogetPwdActivity.class, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.requestQueue.cancelAll(this);
        finish();
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastManager.showToastReal("再按一次退出程序");
                exitTime= System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
        }
        return true;
    }
}
