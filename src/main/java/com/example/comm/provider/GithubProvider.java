package com.example.comm.provider;

import com.alibaba.fastjson.JSON;
import com.example.comm.DTO.AccessTokenDTO;
import com.example.comm.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

    public GithubUser getUser(String accsessToken) {
        OkHttpClient client = new OkHttpClient();
        String[] s1=accsessToken.split("&");
        String[] s2=s1[0].split("=");
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+s2[1])
                    .build();

            try (Response response = client.newCall(request).execute()) {
                GithubUser githubUser=JSON.parseObject(response.body().string(), GithubUser.class);
                return githubUser;
            }
         catch (Exception e){


         }
        return null;


    }

    }

