package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.MyFollowingListResponse;
import com.nemesiss.dev.ianime.Services.UserServices;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class GetMyFollowerTask extends CustomPostExecuteAsyncTask<Void, Void, MyFollowingListResponse> {
    private OkHttpClient okHttpClient;
    public  GetMyFollowerTask(TaskPostExecuteWrapper<MyFollowingListResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected MyFollowingListResponse doInBackground(Void ... voids) {
        try {

            Request request = new Request.Builder()
                    .url(APIDocs.GetMyFollower)
                    .addHeader("Authorization", "Bearer " + UserServices.GetAccessToken())
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                MyFollowingListResponse myFollowingListResponse = gson.fromJson(responseData, MyFollowingListResponse.class);
                return myFollowingListResponse;
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
