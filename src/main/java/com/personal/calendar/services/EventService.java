package com.personal.calendar.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.personal.calendar.dao.EventMapper;
import com.personal.calendar.dto.Event;

@Service
public class EventService {
    private final EventMapper eventMapper;

    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public List<Event> getEvents() {
        return eventMapper.getEvents();
    }

    public void refreshEvents() {
        eventMapper.removeObsoleteEvents();

        List<Event> events = eventMapper.getEvents();
        for (Event event : events) {
            //permanent events
            if (event.getType().getDuration().equals("P")) {
                //if old, then set current year
                if (event.getDate().before(new Date())) {                ;
                    Date date = new Date(event.getDate().getTime());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(Calendar.YEAR, LocalDate.now().getYear() + 1);

                    Map<String, Object> request = new HashMap<>();
                    request.put("date", calendar.getTimeInMillis());
                    request.put("idEvent", event.getIdEvent());
                    eventMapper.renewPermanentEvents(request);
                }
            }
        }
    }

    public List<Event> getEventsCalendar(String year, String month) {
        //convert year and month to long
        Instant firstDayOfMonth = LocalDateTime.parse(year.toString() + "-" + month + "-01 00:00:00", DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss"))
                                .atZone(ZoneId.of( "UTC" ))
                                .toInstant();

        Instant lastDayOfMonth = LocalDateTime.parse(year.toString() + "-" + month + "-01 00:00:00", DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss"))
                            .atZone(ZoneId.of( "UTC" ))
                            .plusMonths(1)
                            .minusDays(1)
                            .toInstant();

        Map<String, Object> map = new HashMap<>();
        map.put("startDate", firstDayOfMonth.toEpochMilli());
        map.put("endDate", lastDayOfMonth.toEpochMilli());

        return eventMapper.getEventsCalendar(map);
    }

    public Event getEvent(Long idEvent) {
        return eventMapper.getEvent(idEvent);
    }

    public void insertEvent(Event event) {
        eventMapper.insertEvent(event);
    }

    public void updateEvent(Event event) {
        eventMapper.updateEvent(event);
    }

    public void deleteEvent(Long idEvent) {
        eventMapper.deleteEvent(idEvent);
    }
}
