package com.eventu.event_service.services;

import com.eventu.event_service.controllers.request.EventRequest;
import com.eventu.event_service.model.Event;
import com.eventu.event_service.services.interfaces.EventService;

import java.util.List;
import java.util.UUID;

public class EventServiceImplementation implements EventService {
    @Override
    public Event createEvent(EventRequest request) {
        Event createdEvent = new Event();
        createdEvent.setEventNumber(UUID.randomUUID().toString());
        createdEvent.setTitle(request.getTitle());
        createdEvent.setDescription(request.getDescription());
        createdEvent.setShortDescription(request.getShortDescription());
        createdEvent.setStarTime(request.getStarTime());
        return null;
    }

    @Override
    public Event updateEvent(Long id, EventRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteEvent(Long id) throws Exception {

    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public List<Event> searchEvents(String keyword) {
        return null;
    }

    @Override
    public Event findEventById(Long emprendimientoId) throws Exception {
        return null;
    }
}
