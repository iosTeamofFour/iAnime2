package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Response.WorkDetailsResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetWorkDetailsTask extends CustomPostExecuteAsyncTask<Integer, Void, List<WorkDetailsResponse>> {
    private OkHttpClient okHttpClient;
    public  GetWorkDetailsTask(TaskPostExecuteWrapper<List<WorkDetailsResponse>> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    protected List<WorkDetailsResponse> doInBackground(Integer ... integers) {
        try {
            String fullAPI=APIDocs.GetWorkDetails+integers[0];
            Request request = new Request.Builder()
                    .url(fullAPI)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                Gson gson = new Gson();
                List<WorkDetailsResponse> resp = gson.fromJson(responseData, new TypeToken<List<WorkDetailsResponse>>() {
                }.getType());
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

