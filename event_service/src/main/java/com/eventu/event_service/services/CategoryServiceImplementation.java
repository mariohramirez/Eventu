package com.eventu.event_service.services;

import com.eventu.event_service.model.gateway.CategoryRepository;
import com.eventu.event_service.services.request.CategoryRequest;
import com.eventu.event_service.model.Category;
import com.eventu.event_service.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryRequest request) {

        Category createdCategory = new Category();
        createdCategory.setName(request.getName());
        createdCategory.setDescription(request.getDescription());
        createdCategory.setIcon(request.getIcon());

        return categoryRepository.save(createdCategory);
    }

    @Override
    public Category updateCategory(Integer id, CategoryRequest request) throws Exception {
        Category category = findCategoryById(id);
        if(request.getDescription()!=null){
            category.setDescription(request.getDescription());
        }
        if(request.getIcon()!=null){
            category.setIcon(request.getIcon());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) throws Exception {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Integer id) throws Exception {
        Optional<Category> opt = categoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("No se ha encontrado la categoria con el id");
        }
        return opt.get();
    }
}
