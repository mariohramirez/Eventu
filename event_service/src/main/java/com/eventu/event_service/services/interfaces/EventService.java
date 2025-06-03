package com.eventu.event_service.services.interfaces;

import com.eventu.event_service.controllers.request.EventRequest;
import com.eventu.event_service.model.Event;

import java.util.List;

public interface EventService {

    public Event createEvent(EventRequest request);

    public Event updateEvent(Long id, EventRequest request)throws Exception;

    public void deleteEvent(Long id)throws Exception;

    public List<Event> getAllEvents();

    public List<Event> searchEvents(String keyword);

    public Event  findEventById(Long emprendimientoId) throws Exception;
}
