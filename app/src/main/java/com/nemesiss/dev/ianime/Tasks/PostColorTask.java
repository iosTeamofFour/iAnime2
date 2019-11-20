package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.PostColorRequestInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.PostColorResponse;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.PostRequest;

public class PostColorTask extends CustomPostExecuteAsyncTask<PostColorRequestInfo,
        Void, PostColorResponse> {
    private OkHttpClient okHttpClient;

    public PostColorTask(TaskPostExecuteWrapper<PostColorResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public PostColorResponse doInBackground(PostColorRequestInfo... postColorRequestInfos) {

        Gson gson = new Gson();
        String result = gson.toJson(postColorRequestInfos[0], PostColorRequestInfo.class);
        String responseData = PostRequest(APIDocs.PostColorRequest, result);
        PostColorResponse resp = gson.fromJson(responseData, PostColorResponse.class);
        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}
