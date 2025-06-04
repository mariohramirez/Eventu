package com.eventu.event_service.services.interfaces;

import com.eventu.event_service.model.Tag;
import com.eventu.event_service.services.request.TagRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    public Tag createTag(TagRequest request);

    public Tag updateTag(Integer id, TagRequest request) throws Exception;

    public void deleteTag(Integer id)throws Exception;

    public List<Tag> getAllTags();

    public Tag  findTagById(Integer id) throws Exception;

}
