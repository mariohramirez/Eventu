package com.eventu.event_service.controllers;



import com.eventu.event_service.model.Tag;
import com.eventu.event_service.services.interfaces.TagService;
import com.eventu.event_service.services.request.TagRequest;
import com.eventu.event_service.services.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping()
    public ResponseEntity<Tag> createTag(@RequestBody TagRequest req){
        Tag tag = tagService.createTag(req);
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag
            (@PathVariable Integer id, @RequestBody TagRequest req) throws Exception{
        Tag tag = tagService.updateTag(id, req);
        return new ResponseEntity<>(tag, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTag(
            @PathVariable Integer id
    )throws Exception{
        tagService.deleteTag(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("El tag se ha eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        List<Tag> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(
            @PathVariable Integer id
    )throws Exception{
        Tag tag = tagService.findTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
