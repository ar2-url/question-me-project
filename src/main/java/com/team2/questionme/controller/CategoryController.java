package com.team2.questionme.controller;

import com.team2.questionme.dto.AddQuestionDTO;
import com.team2.questionme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCategories(){
        List<String> all = categoryService.getAll();
        return new ResponseEntity(all, HttpStatus.OK);
    }
}

