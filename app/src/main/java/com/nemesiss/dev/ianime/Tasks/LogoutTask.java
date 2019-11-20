package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.GetRequest;

public class LogoutTask extends CustomPostExecuteAsyncTask<Void, Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public LogoutTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected CommonResponse doInBackground(Void... voids) {
        String responseData = GetRequest(APIDocs.Logout);
        Gson gson = new Gson();
        CommonResponse commonResponse = gson.fromJson(responseData, CommonResponse.class);
        return commonResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
