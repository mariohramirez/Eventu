package com.eventu.event_service.services;

import com.eventu.event_service.model.Tag;
import com.eventu.event_service.model.gateway.TagRepository;
import com.eventu.event_service.services.interfaces.TagService;
import com.eventu.event_service.services.request.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createTag(TagRequest request) {
        Tag createdTag = new Tag();
        createdTag.setColor(request.getColor());
        createdTag.setName(request.getName());
        return tagRepository.save(createdTag);
    }

    @Override
    public Tag updateTag(Integer id, TagRequest request) throws Exception{
        Tag tag = findTagById(id);
        if(request.getColor()!=null){
            tag.setColor(request.getColor());
        }
        if(request.getName()!=null){
            tag.setName(request.getName());
        }
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer id) throws Exception {
        Tag tag  = findTagById(id);
        tagRepository.delete(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findTagById(Integer id) throws Exception {
        Optional<Tag> opt = tagRepository.findById(id);
        if(opt.isEmpty())
        {
            throw  new Exception("No se ha encontrado el elemento");
        }
        return opt.get();
    }
}
