package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.UpdateAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Request.LoginAndRegisterAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class UpdateAccountInfoTask extends CustomPostExecuteAsyncTask<UpdateAccountInfo,
        Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public UpdateAccountInfoTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public CommonResponse doInBackground(UpdateAccountInfo... accountInfos) {
        try {
            Gson gson = new Gson();
            String result = gson.toJson(accountInfos[0], LoginAndRegisterAccountInfo.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.UpdateAccountInfo)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                CommonResponse resp = gson.fromJson(responseData, CommonResponse.class);
                return resp;
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

