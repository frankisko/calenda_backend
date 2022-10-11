package com.personal.calendar.controllers.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.calendar.controllers.event.requests.EventPagingRequest;
import com.personal.calendar.dao.EventMapper;
import com.personal.calendar.dto.datatables.DatatablesRequest;
import com.personal.calendar.dto.datatables.PagingResponse;
import com.personal.calendar.dto.Event;
import com.personal.calendar.services.EventService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

	private final EventService eventService;

	private final EventMapper eventMapper;

	@Autowired
	public EventController(EventService eventService, EventMapper eventMapper) {
		this.eventService = eventService;
		this.eventMapper = eventMapper;
	}

	@GetMapping("/events")
	public List<Event> getEvents() {
		return eventService.getEvents();
	}

	@GetMapping("/events/refresh")
	public void refreshEvents() {
		eventService.refreshEvents();
	}

	@PostMapping(value="/events/paged")
	public PagingResponse<Event> pagedList(@RequestBody DatatablesRequest request) {
		PagingResponse<Event> pagingResponse = new PagingResponse<>();

		//EventPagingRequest pagingRequest = (EventPagingRequest) request;

		DatatablesRequest pagingRequest = request;

	    Map<String, Object> params = new HashMap<>();

        params.put("order", request.getOrder());

        params.put("search", null);
        if (pagingRequest.getSearch() != null && pagingRequest.getSearch().getValue() != null && !pagingRequest.getSearch().getValue().equalsIgnoreCase("")) {
            params.put("search", pagingRequest.getSearch().getValue());
        }

        params.put("start", pagingRequest.getStart());
        params.put("length", pagingRequest.getLength());

        CompletableFuture<List<Event>> list = CompletableFuture.supplyAsync(() -> eventMapper.pagedList(params));
        CompletableFuture<Integer> count = CompletableFuture.supplyAsync(() -> eventMapper.countEvent(params));

        try {
            CompletableFuture.allOf(list, count).get();

            pagingResponse.setData(list.get());
            pagingResponse.setRecordsTotal(count.get());
            pagingResponse.setRecordsFiltered(list.get().size());
            pagingResponse.setDraw(request.getDraw());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

		return pagingResponse;
	}

	@GetMapping("/events/calendar/{year}/{month}")
	public List<Event> getEventsCalendar(@PathVariable String year, @PathVariable String month) {
		return eventService.getEventsCalendar(year, month);
	}

	@GetMapping("/events/{idEvent}")
	public Event getEvent(@PathVariable Long idEvent) {
		return eventService.getEvent(idEvent);
	}

	@PostMapping("/events")
	public void insertEvent(@RequestBody Event event) {
		event.setStatus("A");
		eventService.insertEvent(event);
	}

	@PutMapping("/events/{idEvent}")
	public void updateEvent(@RequestBody Event event, @PathVariable Long idEvent) {
		event.setIdEvent(idEvent);
		eventService.updateEvent(event);
	}

	@DeleteMapping("/events/{idEvent}")
	public void deleteEvent(@PathVariable Long idEvent) {
		eventService.deleteEvent(idEvent);
	}
}