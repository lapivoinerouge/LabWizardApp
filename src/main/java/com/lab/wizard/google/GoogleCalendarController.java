package com.lab.wizard.google;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.api.services.calendar.Calendar;
import com.lab.wizard.config.AdminConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;

@Controller
public class GoogleCalendarController {

    private final static Log logger = LogFactory.getLog(GoogleCalendarController.class);
    private static final String APPLICATION_NAME = "LabWizardApp";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static com.google.api.services.calendar.Calendar client;
    private static final Long EXPIRES_IN_SECONDS = 3600L;

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;

    @Autowired
    private AdminConfig adminConfig;

    private Set<Event> events = new HashSet<>();

    final DateTime date1 = new DateTime("2019-11-10T16:30:00.000+01:00");
    final DateTime date2 = new DateTime(new Date());

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public ResponseEntity<String> oauth2Callback() throws Exception {
        com.google.api.services.calendar.model.Events eventList;
        String message;

        TokenResponse tokenResponse = new TokenResponse();

        //this will be replaced by the value generated in the method
        tokenResponse.setAccessToken("ya29.Il-xB3VDzq1Cgu-4hrR0w0GpbzXMNGKHbDd-pK8SxjqCshYFquVyYDqXEO2OxwYmdsfd4EAC4ABu9eudUa-49oqoxTPStV5AR74xa2p4LfVYpoU0X_B2epiQdLf-gUfFXQ");

        tokenResponse.setTokenType(adminConfig.getTokenType());
        tokenResponse.setExpiresInSeconds(EXPIRES_IN_SECONDS);
        tokenResponse.setRefreshToken(adminConfig.getRefreshToken());
        tokenResponse.setScope(adminConfig.getScope());

        GoogleClientSecrets.Details web = new GoogleClientSecrets.Details();
        web.setClientId(adminConfig.getClientId());
        web.setClientSecret(adminConfig.getClientSecret());
        clientSecrets = new GoogleClientSecrets().setWeb(web);
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).build();

        try {
            credential = flow.createAndStoreCredential(tokenResponse, "userID");
            client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            Calendar.Events events = client.events();
            eventList = events.list("vjqiss4v2deaig60dqgbe053qk@group.calendar.google.com").setTimeMin(date1).setTimeMax(date2).execute();
            message = eventList.getItems().toString();
            System.out.println("My:" + eventList.getItems());
        } catch (Exception e) {
            logger.warn("Exception while handling OAuth2 callback (" + e.getMessage());
            message = "Exception while handling OAuth2 callback (" + e.getMessage();
        }

        System.out.println("Calendar message:" + message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //POST EVENT TO IMPLEMENT HERE

    public Set<Event> getEvents() throws IOException {
        return this.events;
    }
}
