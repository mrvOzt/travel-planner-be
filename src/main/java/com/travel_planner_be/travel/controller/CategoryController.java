package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.entity.Category;
import com.travel_planner_be.travel.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestParam String name) {
        return categoryService.addCategory(name);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam String name) {
        return categoryService.deleteCategory(name);
    }

}
