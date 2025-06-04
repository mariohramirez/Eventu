package com.eventu.event_service.controllers;



import com.eventu.event_service.model.Location;
import com.eventu.event_service.services.interfaces.LocationService;
import com.eventu.event_service.services.request.LocationRequest;
import com.eventu.event_service.services.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping()
    public ResponseEntity<Location> createLocation(@RequestBody LocationRequest req){
        Location location = locationService.createLocation(req);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation
            (@PathVariable Long id, @RequestBody LocationRequest req) throws Exception{
        Location location = locationService.updateLocation(id, req);
        return new ResponseEntity<>(location, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteLocation(
            @PathVariable Long id
    )throws Exception{
        locationService.deleteLocation(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("La ubicacion se ha eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(
            @PathVariable Long id
    )throws Exception{
        Location location = locationService.findLocationById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
