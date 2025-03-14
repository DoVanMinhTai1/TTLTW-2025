package vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.GoogleProfile;
import vn.edu.hcmuaf.fit.projectwebck.utils.Constant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GoogleLogin {
    public static String getToken(String code) throws IOException {
        String response = Request.post(Constant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", Constant.GOOGLE_CLIENT_ID)

                        .add("client_secret", Constant.GOOGLE_CLIENT_SECRET)

                        .add("redirect_uri", Constant.GOOGLE_REDIRECT_URI)

                        .add("code", code)

                        .add("grant_type", Constant.GOOGLE_GRANT_TYPE)

                        .build()

                )

                .execute().returnContent().asString();


        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;


    }

    public static GoogleProfile getUserInfo(final String accessToken) throws IOException {

        String link = Constant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = new String(Request.get(link).execute().returnContent().asBytes(), StandardCharsets.UTF_8);
        GoogleProfile profile = new Gson().fromJson(response, GoogleProfile.class);
        return profile;
    }
}

