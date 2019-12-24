package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.AccountInfoResponse;
import com.nemesiss.dev.ianime.Services.UserServices;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.GetRequest;

public class GetAccountInfoTask extends CustomPostExecuteAsyncTask<String, Void,
        AccountInfoResponse> {
    private OkHttpClient okHttpClient;

    public GetAccountInfoTask(TaskPostExecuteWrapper<AccountInfoResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected AccountInfoResponse doInBackground(String... strings) {

        String FullAPI;

        // TODO Extract UserID From JWT Token
        FullAPI = APIDocs.GetAccountInfo + strings[0];

        String responseData = GetRequest(FullAPI);
        Gson gson = new Gson();
        AccountInfoResponse accountInfoResponse = gson.fromJson(responseData, AccountInfoResponse.class);
        return accountInfoResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
