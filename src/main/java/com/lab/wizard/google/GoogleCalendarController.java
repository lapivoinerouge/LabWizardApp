package com.lab.wizard.google;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.EventDateTime;
import com.lab.wizard.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoogleCalendarController {

    private static final String APPLICATION_NAME = "LabWizardApp";
    private static final String CALENDAR_ID = "vjqiss4v2deaig60dqgbe053qk@group.calendar.google.com";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final Long EXPIRES_IN_SECONDS = 3600L;
    private static com.google.api.services.calendar.Calendar client;
    private static HttpTransport httpTransport;
    private TokenResponse tokenResponse = new TokenResponse();
    private Set<Event> events = new HashSet<>();

    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;

    @Autowired
    private AdminConfig adminConfig = AdminConfig.getInstance();

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public ResponseEntity<String> oauth2Callback() throws Exception {
        com.google.api.services.calendar.model.Events eventList;
        String message;

        setAccess();

        DateTime date1 = new DateTime(LocalDateTime.now().toString().concat(":00+01:00"));
        DateTime date2 = new DateTime(LocalDateTime.now().plusMonths(3L).toString().concat(":00+01:00"));

        try {
            credential = flow.createAndStoreCredential(tokenResponse, "userID");
            client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            Calendar.Events events = client.events();
            eventList = events.list(CALENDAR_ID).setTimeMin(date1).setTimeMax(date2).execute();
            message = eventList.getItems().toString();
            System.out.println("My events:" + eventList.getItems());
        } catch (Exception e) {
            System.out.println("Exception while handling OAuth2 callback");
            message = "Exception while handling OAuth2 callback (" + e.getMessage();
        }

        System.out.println("Calendar message:" + message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/google/create", method = RequestMethod.POST)
    public void createEvent(@RequestBody CalendarEvent calendarEvent) throws Exception {

        Event event = new Event()
                .setSummary(calendarEvent.getName());

        String startTime = calendarEvent.getStartDate().toString().concat(":00+01:00");
        DateTime startDateTime = new DateTime(startTime);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);
        event.setStart(start);

        String endTime = calendarEvent.getEndDate().toString().concat(":00+01:00");
        DateTime endDateTime = new DateTime(endTime);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime);
        event.setEnd(end);

        String calendarId = CALENDAR_ID;

        setAccess();

        credential = flow.createAndStoreCredential(tokenResponse, "userID");
        client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
        Calendar.Events events = client.events();
        events.insert(calendarId, event).execute();

        System.out.println("Event created.");
    }

    public void setAccess() throws Exception {
        tokenResponse.setAccessToken(adminConfig.getAccessToken());
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
    }

    public Set<Event> getEvents() throws IOException {
        return this.events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
