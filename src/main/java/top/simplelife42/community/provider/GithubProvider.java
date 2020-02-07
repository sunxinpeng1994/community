package top.simplelife42.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import top.simplelife42.community.dto.AccesstokenDTO;
import top.simplelife42.community.dto.GithubUser;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccesstoken(AccesstokenDTO accesstokenDTO){
        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String url = "https://github.com/login/oauth/access_token";
        RequestBody body = RequestBody.create(com.alibaba.fastjson.JSON.toJSONString(accesstokenDTO), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String tokenStr = split[0];
            String token = tokenStr.split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }




}
