package com.lecongdung.testvnu.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.lecongdung.testvnu.remote.entity.BodyLogin;

public class SessionManager {
    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    public int PRE_MODE = 1;
    public static final String NAME = "testvnu";
    public static final String KEY_LOGIN = "islogin";
    public static final String USERNAME_LOGIN = "usernamelogin";
    public static final String PASSWORD_LOGIN = "passwordlogin";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public void SetLogin(boolean isLogin) {
        editor.putBoolean(KEY_LOGIN,isLogin);
        editor.commit();
    }
    public void SetLogin(boolean isLogin, String username, String password) {
        editor.putBoolean(KEY_LOGIN,isLogin);
        editor.putString(USERNAME_LOGIN,username);
        editor.putString(PASSWORD_LOGIN,password);
        editor.commit();
    }
    public boolean Check() {
        return preferences.getBoolean(KEY_LOGIN,false);
    }
    public BodyLogin getBodyLogin() {
        String username = preferences.getString(USERNAME_LOGIN,"");
        String password = preferences.getString(PASSWORD_LOGIN,"");
        return new BodyLogin(username,password);
    }

}
