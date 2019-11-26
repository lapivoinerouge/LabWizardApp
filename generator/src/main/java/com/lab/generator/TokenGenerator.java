package com.lab.generator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TokenGenerator {

    @RequestMapping(value = "login/generate", method = RequestMethod.POST)
    public void refreshToken() throws Exception {

        HttpPost post = new HttpPost("https://www.googleapis.com/oauth2/v4/token/");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("client_id", "876675563541-cif2cs5hqdql44scs9dbpr3b4mah4mg0.apps.googleusercontent.com"));
        urlParameters.add(new BasicNameValuePair("client_secret", "rWBp9eWLSpSPMUVAdOdtmIPB"));
        urlParameters.add(new BasicNameValuePair("refresh_token", "1//09BinzoaFidNlCgYIARAAGAkSNwF-L9Ir_ZGy2kWW_5zt2_eHBRAxPSKP_rFQSbnCg4eMSRihdXnWvL7_tLciqDthnbokUKucLT8"));
        urlParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
}
