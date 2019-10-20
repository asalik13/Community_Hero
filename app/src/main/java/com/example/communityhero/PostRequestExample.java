package com.example.communityhero;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import com.google.gson.Gson;

public class PostRequestExample {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        System.out.println("RESPONSE: " + response.toString());
        Gson gson = new Gson();
        ResponseBody responseBody = client.newCall(request).execute().body();
        //SimpleEntity entity = gson.fromJson(responseBody.string(), SimpleEntity.class);

        return response.body().string();
    }

}