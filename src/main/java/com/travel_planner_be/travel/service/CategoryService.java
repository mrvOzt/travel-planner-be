package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.entity.Category;
import com.travel_planner_be.travel.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public ResponseEntity<Category> addCategory(String categoryName) {
        if(findNameById(categoryName).isEmpty()) {
            Category category = new Category(UUID.randomUUID().toString(),categoryName);
            return new ResponseEntity<>(categoryRepository.save(category),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Optional<String> findNameById(String id) {
        Optional<Category> category = categoryRepository.findByName(id);
        return category.map(Category::getName);
    }

    public ResponseEntity<?> deleteCategory(String name) {
        Optional<Category> tempCategory = categoryRepository.findByName(name);
        if(tempCategory.isPresent()) {
            categoryRepository.deleteById(tempCategory.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
