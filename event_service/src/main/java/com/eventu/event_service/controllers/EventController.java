package com.eventu.event_service.controllers;


import com.eventu.event_service.model.Event;
import com.eventu.event_service.services.interfaces.EventService;
import com.eventu.event_service.services.request.EventRequest;
import com.eventu.event_service.services.response.EventResponse;
import com.eventu.event_service.services.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest req) throws Exception {
        Event event = eventService.createEvent(req);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent
            (@PathVariable Long id, @RequestBody EventRequest req) throws Exception{
        EventResponse event = eventService.updateEvent(id, req);
        return new ResponseEntity<>(event, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteEvent(
            @PathVariable Long id
    )throws Exception{
        eventService.deleteEvent(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("El evento se ha eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents(){
        List<EventResponse> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @PathVariable Long id
    )throws Exception{
        Event event = eventService.findEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
