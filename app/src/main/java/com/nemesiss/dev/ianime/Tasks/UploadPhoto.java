package com.nemesiss.dev.ianime.Tasks;

import com.google.gson.Gson;
import com.nemesiss.dev.ianime.Model.Model.Response.UploadAvatarResponse;
import com.nemesiss.dev.ianime.Services.UserServices;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadPhoto {
    public UploadAvatarResponse uploadUserPhoto(String address)throws IOException {
        String url =address;
        File file = new File("avatar.jpg");
        String ext = file.getName().split(".")[1];
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/"+ext),file);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(),fileBody)
                .build();
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","Bearer " + UserServices.GetAccessToken());
        Headers header = Headers.of(headers);
        Request request = new Request
                .Builder()
                .post(body)
                .headers(header)
                .url(url)
                .addHeader("Authorization", "Bearer " +
                        UserServices.GetAccessToken())
                .build();
        OkHttpClient client = new OkHttpClient();
        Response resp = client.newCall(request).execute();
        if(resp.isSuccessful())
        {
            String responseData = resp.body().string();
            Gson gson=new Gson();
            UploadAvatarResponse uploadAvatarResponse = gson.fromJson(responseData, UploadAvatarResponse.class);
            return uploadAvatarResponse;
        }
        else
            return null;
    }
}

