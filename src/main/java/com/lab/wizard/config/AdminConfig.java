package com.lab.wizard.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;
    @Value("${google.client.client-id}")
    private String clientId;
    @Value("${google.client.client-secret}")
    private String clientSecret;
    @Value("${google.client.redirectUri}")
    private String redirectURI;
    @Value("Bearer")
    private String tokenType;
    //REPLACE VALUE WITH REAL ONE
    @Value("REFRESH TOKEN")
    private String refreshToken;
    @Value("\"https://www.googleapis.com/auth/calendar\"")
    private String scope;

}
