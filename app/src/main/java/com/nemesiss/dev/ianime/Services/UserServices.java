package com.nemesiss.dev.ianime.Services;

import android.content.Context;
import android.content.SharedPreferences;
import com.nemesiss.dev.ianime.Application.iAnimeApplication;

import static android.content.Context.MODE_PRIVATE;

public class UserServices {
    private static Context ctx;
    private static SharedPreferences sp;
    public UserServices() {
        ctx = iAnimeApplication.getContext();
        sp = ctx.getSharedPreferences("LoginReturnData",MODE_PRIVATE);
    }
    public static String GetAlias() {
        return sp.getString("Alias",null);
    }
    public static void RemoveAlias() {
        SharedPreferences.Editor EditSp = sp.edit();
        EditSp.putBoolean("HaveSetAlias",false);
        EditSp.apply();
    }

    public static void PersistAlias(String alias) {
        SharedPreferences.Editor EditSp = sp.edit();
        EditSp.putBoolean("HaveSetAlias",true);
        EditSp.putString("Alias",alias);
        EditSp.apply();
    }
    public static String GetUserID()
    {
        return sp.getString("UserID","");
    }
    public static String GetAccessToken()
    {
        return sp.getString("AccessToken"+GetUserID(),"");
    }
}
