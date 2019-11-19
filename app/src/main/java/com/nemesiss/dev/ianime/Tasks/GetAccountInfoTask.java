package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.AccountInfoResponse;
import com.nemesiss.dev.ianime.Services.UserServices;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class GetAccountInfoTask extends CustomPostExecuteAsyncTask<String, Void,
        AccountInfoResponse> {
    private OkHttpClient okHttpClient;

    public GetAccountInfoTask(TaskPostExecuteWrapper<AccountInfoResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected AccountInfoResponse doInBackground(String... strings) {
        try {
            String FullAPI;
            if(strings[0]!=null) {
               FullAPI = APIDocs.GetAccountInfo + strings[0];
            }
            else
            {
               FullAPI=APIDocs.GetAccountInfo+UserServices.GetUserID();
            }
            Request request = new Request.Builder()
                    .url(FullAPI)
                    .addHeader("Authorization", "Bearer " + UserServices.GetAccessToken())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                AccountInfoResponse accountInfoResponse = gson.fromJson(responseData, AccountInfoResponse.class);
                return accountInfoResponse;
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
