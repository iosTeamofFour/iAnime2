package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.MyFollowingListResponse;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.GetRequest;

public class GetMyFollowingListTask extends CustomPostExecuteAsyncTask<Void, Void,MyFollowingListResponse> {
    private OkHttpClient okHttpClient;
    public  GetMyFollowingListTask(TaskPostExecuteWrapper<MyFollowingListResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected MyFollowingListResponse doInBackground(Void ... voids) {
        String responseData = GetRequest(APIDocs.GetMyFollowing);
        Gson gson = new Gson();
        MyFollowingListResponse myFollowingListResponse = gson.fromJson(responseData, MyFollowingListResponse.class);
        return myFollowingListResponse;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
