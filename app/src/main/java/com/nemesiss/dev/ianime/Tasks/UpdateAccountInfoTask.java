package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.InfrastructureExtension.CustomPostExecuteAsyncTask;
import com.nemesiss.dev.ianime.InfrastructureExtension.TaskPostExecuteWrapper;
import com.nemesiss.dev.ianime.Model.Model.APIDocs;
import com.nemesiss.dev.ianime.Model.Model.Request.LoginAndRegisterAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Request.UpdateAccountInfo;
import com.nemesiss.dev.ianime.Model.Model.Response.CommonResponse;
import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;
import static com.nemesiss.dev.ianime.Utils.AppUtils.PostRequest;

public class UpdateAccountInfoTask extends CustomPostExecuteAsyncTask<UpdateAccountInfo,
        Void, CommonResponse> {
    private OkHttpClient okHttpClient;
    public UpdateAccountInfoTask(TaskPostExecuteWrapper<CommonResponse> DoInPostExecute) {
        super(DoInPostExecute);
    }

    @Override
    public CommonResponse doInBackground(UpdateAccountInfo... accountInfos) {

        Gson gson = new Gson();
        String result = gson.toJson(accountInfos[0], LoginAndRegisterAccountInfo.class);
        String responseData = PostRequest(APIDocs.UpdateAccountInfo, result);
        CommonResponse resp = gson.fromJson(responseData, CommonResponse.class);
        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(4500, TimeUnit.MILLISECONDS).build();
    }
}

