package com.nemesiss.dev.ianime.Services;

import android.content.Context;
import android.content.SharedPreferences;
import com.nemesiss.dev.ianime.Application.iAnimeApplication;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.Request.LoginAndRegisterAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import com.nemesiss.dev.ianime.Model.Model.Response.LoginResponse;
import com.nemesiss.dev.ianime.Tasks.PostLoginInfoTask;
import com.nemesiss.dev.ianime.Tasks.PostRegisterInfoTask;

import static android.content.Context.MODE_PRIVATE;

public class UserServices {



    private static Context ctx;
    private static SharedPreferences sp;

    static {
        ctx = iAnimeApplication.getContext();
        sp = ctx.getSharedPreferences("LoginReturnData",MODE_PRIVATE);
    }

    public static boolean SaveAccessToken(String token) {
        SharedPreferences.Editor EditToken =  sp.edit();
        EditToken.putString("JWT",token);
        return EditToken.commit();
    }

    public static String GetAccessToken() {
        return sp.getString("JWT", "");
    }

    public UserServices() {

    }

    public void Login(LoginAndRegisterAccountInfo LoginInfo, TaskPostExecuteWrapper<LoginResponse> HandleResult) {
        new PostLoginInfoTask(LoginResult -> {
            if(LoginResult.getStatusCode() == 0 && !SaveAccessToken(LoginResult.getToken())) {
                throw new IllegalStateException("Cannot save JWT Token to SharedPreferences");
            }
            HandleResult.DoOnPostExecute(LoginResult);
        }).execute(LoginInfo);
        // HOC 高阶组件 业务逻辑增强

    }

    public void Register(LoginAndRegisterAccountInfo RegisterInfo, TaskPostExecuteWrapper<CommonResponse> HandleResult) {
        new PostRegisterInfoTask(HandleResult).execute(RegisterInfo);
    }
}
