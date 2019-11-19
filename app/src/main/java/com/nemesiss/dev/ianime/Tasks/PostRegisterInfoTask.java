package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.LoginAndRegisterAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import com.nemesiss.dev.ianime.Model.Model.Response.LoginResponse;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.HMacSha256.Encrypt;

public class PostRegisterInfoTask extends CustomPostExecuteAsyncTask<LoginAndRegisterAccountInfo,
        Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public PostRegisterInfoTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public CommonResponse doInBackground(LoginAndRegisterAccountInfo... registerAccountInfos) {
        try {
            String afterEncryption = Encrypt(registerAccountInfos[0].getPassword());
            registerAccountInfos[0].setPassword(afterEncryption);
            Gson gson = new Gson();
            String result = gson.toJson(registerAccountInfos[0], LoginAndRegisterAccountInfo.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.Register)
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
