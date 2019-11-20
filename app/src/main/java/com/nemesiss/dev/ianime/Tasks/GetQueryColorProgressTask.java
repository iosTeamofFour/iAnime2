package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.QueryColorProgressResponse;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import static com.nemesiss.dev.ianime.Utils.AppUtils.GetRequest;

public class GetQueryColorProgressTask extends CustomPostExecuteAsyncTask<String, Void,
        QueryColorProgressResponse> {
    private OkHttpClient okHttpClient;

    public GetQueryColorProgressTask(TaskPostExecuteWrapper<
            QueryColorProgressResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected QueryColorProgressResponse doInBackground(String... strings) {

        String responseData = GetRequest(APIDocs.QueryColorProgress + strings[0]);
        Gson gson = new Gson();
        QueryColorProgressResponse resp =
                gson.fromJson(responseData, QueryColorProgressResponse.class);
        return resp;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}

