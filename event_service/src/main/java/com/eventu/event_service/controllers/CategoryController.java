package com.eventu.event_service.controllers;


import com.eventu.event_service.model.Category;
import com.eventu.event_service.services.interfaces.CategoryService;
import com.eventu.event_service.services.request.CategoryRequest;
import com.eventu.event_service.services.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest req){
        Category category = categoryService.createCategory(req);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory
            (@PathVariable Integer id, @RequestBody CategoryRequest req) throws Exception{
        Category category = categoryService.updateCategory(id, req);
        return new ResponseEntity<>(category, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(
            @PathVariable Integer id
    )throws Exception{
        categoryService.deleteCategory(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("La categoria se ha eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Integer id
    )throws Exception{
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
