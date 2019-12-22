package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.FollowOrCancel;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.PostRequest;

public class FollowOrCancelTask extends CustomPostExecuteAsyncTask<FollowOrCancel,
        Void, CommonResponse> {
    private OkHttpClient okHttpClient;

    public FollowOrCancelTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public CommonResponse doInBackground(FollowOrCancel... followOrCancels) {

        Gson gson = new Gson();
        String result = gson.toJson(followOrCancels[0], FollowOrCancel.class);
        String responseData = PostRequest(APIDocs.FollowOrCancel, result);
        CommonResponse resp = gson.fromJson(responseData, CommonResponse.class);
        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}

