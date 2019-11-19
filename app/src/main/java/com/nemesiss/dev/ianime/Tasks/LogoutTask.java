package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import com.nemesiss.dev.ianime.Services.UserServices;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class LogoutTask extends CustomPostExecuteAsyncTask<Void, Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    public LogoutTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected CommonResponse doInBackground(Void ... voids) {
        try {
            Request request = new Request.Builder()
                    .url(APIDocs.Logout)
                    .addHeader("Authorization","Bearer "+ UserServices.GetAccessToken())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                String responseData = response.body().string();
                Gson gson = new Gson();
                CommonResponse commonResponse = gson.fromJson(responseData, CommonResponse.class);
                return commonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
