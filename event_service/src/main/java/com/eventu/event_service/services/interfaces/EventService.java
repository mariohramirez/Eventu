package com.eventu.event_service.services.interfaces;

import com.eventu.event_service.services.request.EventRequest;
import com.eventu.event_service.model.Event;
import com.eventu.event_service.services.response.EventResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    public Event createEvent(EventRequest request) throws Exception;

    public EventResponse updateEvent(Long id, EventRequest request)throws Exception;

    public void deleteEvent(Long id)throws Exception;

    public List<EventResponse> getAllEvents();

    public List<Event> searchEvents(String keyword);

    public Event  findEventById(Long emprendimientoId) throws Exception;
}
