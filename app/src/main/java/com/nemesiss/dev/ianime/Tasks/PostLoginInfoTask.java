package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.LoginAndRegisterAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.LoginResponse;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.HMacSha256.Encrypt;

public class PostLoginInfoTask extends CustomPostExecuteAsyncTask<LoginAndRegisterAccountInfo,
        Void, LoginResponse> {
    private OkHttpClient okHttpClient;

    public PostLoginInfoTask(TaskPostExecuteWrapper<LoginResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public LoginResponse doInBackground(LoginAndRegisterAccountInfo... loginAccountInfos) {
        try {
            String afterEncryption = Encrypt(loginAccountInfos[0].getPassword());
            loginAccountInfos[0].setPassword(afterEncryption);
            Gson gson = new Gson();
            String result = gson.toJson(loginAccountInfos[0], LoginAndRegisterAccountInfo.class);
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json"), result);
            Request request = new Request.Builder()
                    .url(APIDocs.Login)
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                LoginResponse resp = gson.fromJson(responseData, LoginResponse.class);
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
