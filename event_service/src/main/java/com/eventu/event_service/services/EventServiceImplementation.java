package com.eventu.event_service.services;

import com.eventu.event_service.model.*;
import com.eventu.event_service.model.gateway.*;
import com.eventu.event_service.services.interfaces.LocationService;
import com.eventu.event_service.services.request.CategoryRequest;
import com.eventu.event_service.services.request.EventRequest;
import com.eventu.event_service.services.interfaces.EventService;
import com.eventu.event_service.services.request.LocationRequest;
import com.eventu.event_service.services.request.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventCategoriesRepository eventCategoriesRepository;

    @Autowired
    private EventTagRepository eventTagRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TagServiceImplementation tagServiceImplementation;

    @Autowired
    private CategoryServiceImplementation categoryServiceImplementation;

    @Override
    public Event createEvent(EventRequest request) throws Exception {
        Event createdEvent = new Event();
        createdEvent.setEventNumber(UUID.randomUUID().toString());
        createdEvent.setTitle(request.getTitle());
        createdEvent.setDescription(request.getDescription());
        createdEvent.setShortDescription(request.getShortDescription());
        createdEvent.setStartTime(request.getStartTime());
        createdEvent.setEndTime(request.getEndTime());

        //Asigna una ubicacion al evento
        //En caso de que la ubicacion ya exista en la base de datos asigna ese
        //En caso de que no exista dicha ubicacion, la crea en la base de datos y la agrega al evento
        if(request.getLocationRequest()!=null){
            LocationRequest locationRequest = request.getLocationRequest();
            Location location;
            if(locationRequest.getId()!=null){
                location = locationService.findLocationById(locationRequest.getId());
                createdEvent.setLocation(location);
            }
            else{
                location = locationService.createLocation(locationRequest);
                createdEvent.setLocation(location);
            }
        }

        createdEvent.setOrganizerId(request.getOrganizerId());
        createdEvent.setStatus(request.getStatus());
        createdEvent.setVisibility(request.getVisibility());
        createdEvent.setMaxAttendees(request.getMaxAttendees());
        createdEvent.setMinAttendees(request.getMinAttendees());
        createdEvent.setCurrentAttendees(0);
        createdEvent.setCreatedAt(LocalDate.now());
        createdEvent.setUpdatedAt(LocalDate.now());

        createdEvent = eventRepository.save(createdEvent);

        //Asignamos los tags que ya hayan sido creados al evento
        //Creamos los tags que no existan en la base de datos y los asignamos al evento
        if(request.getTagRequest()!=null) {
            List<TagRequest> eventRequests = request.getTagRequest();
            for (int i = 0; i <eventRequests.size();i++ )
            {
                TagRequest tagRequest = eventRequests.get(i);
                if(tagRequest.getId()!=null){
                    Tag tag = tagServiceImplementation.findTagById(tagRequest.getId());
                    assignTag(createdEvent, tag);
                }
                else{
                    Tag tag = tagServiceImplementation.createTag(tagRequest);
                    assignTag(createdEvent, tag);
                }
            }
        }

        //Asignamos las categorias al evento
        if(request.getCategoryRequest()!=null) {
            List<CategoryRequest> categoryRequests = request.getCategoryRequest();
            for (int i = 0; i <categoryRequests.size();i++ )
            {
                CategoryRequest categoryRequest = categoryRequests.get(i);
                Category category = categoryServiceImplementation.findCategoryById(categoryRequest.getId());
                assignCategory(createdEvent, category);
            }
        }

        return eventRepository.save(createdEvent);
    }

    @Override
    public Event updateEvent(Long id, EventRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteEvent(Long id) throws Exception {
        Event event = findEventById(id);
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String keyword) {
        return null;
    }

    @Override
    public Event findEventById(Long id) throws Exception {
        Optional<Event> opt = eventRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("No se encuentra elemento");
        }
        return opt.get();
    }

    private void assignTag(Event event, Tag tag){
        EventTagId eventTagId = new EventTagId();
        eventTagId.setEventId(event.getId());
        eventTagId.setTagId(tag.getId());

        EventTags eventTags = new EventTags();
        eventTags.setId(eventTagId);
        eventTags.setEvent(event);
        eventTags.setTag(tag);

        eventTagRepository.save(eventTags);
    }

    private void assignCategory(Event createdEvent, Category category) {

        EventCategoryId eventCategoryId = new EventCategoryId();
        eventCategoryId.setEventId(createdEvent.getId());
        eventCategoryId.setCategoryId(category.getId());

        EventCategories eventCategories = new EventCategories();
        eventCategories.setId(eventCategoryId);
        eventCategories.setEvent(createdEvent);
        eventCategories.setCategory(category);

        eventCategoriesRepository.save(eventCategories);
    }
}
