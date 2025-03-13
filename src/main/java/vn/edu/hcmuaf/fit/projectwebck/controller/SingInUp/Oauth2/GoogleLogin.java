package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.GoogleProfile;
import vn.edu.hcmuaf.fit.projectwebck.utils.Constant;

import java.io.IOException;

public class GoogleLogin {
    public static String getToken(String code) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(Constant.GOOGLE_LINK_GET_TOKEN);

            String body = "client_id=" + Constant.GOOGLE_CLIENT_ID +
                    "&client_secret=" + Constant.GOOGLE_CLIENT_SECRET +
                    "&redirect_uri=" + Constant.GOOGLE_REDIRECT_URI +
                    "&code=" + code +
                    "&grant_type=" + Constant.GOOGLE_GRANT_TYPE;

            post.setEntity(new StringEntity(body));
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

            try (CloseableHttpResponse response = httpClient.execute(post)) {
//                return new String(response.getEntity().getContent().readAllBytes());
                String json = EntityUtils.toString(response.getEntity());
                JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
                return jsonObject.get("access_token").getAsString();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static GoogleProfile getUserInfo(final String accessToken) throws IOException {

        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(link);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                return new Gson().fromJson(json, GoogleProfile.class);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

