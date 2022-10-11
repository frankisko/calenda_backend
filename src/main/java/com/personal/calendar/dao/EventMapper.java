package com.personal.calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.personal.calendar.dto.Event;

@Mapper
public interface EventMapper {

    List<Event> getEvents();

    void removeObsoleteEvents();

    void renewPermanentEvents(Map request);

    List<Event> pagedList(Map request);

    Integer countEvent(Map request);

    List<Event> getEventsCalendar(Map request);

    Event getEvent(Long idEvent);

    void insertEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(Long idEvent);

}