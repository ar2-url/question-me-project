package com.team2.questionme.controller;

import com.team2.questionme.dto.QuestionDTO;
import com.team2.questionme.service.CategoryService;
import com.team2.questionme.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private QuestionService questionService;


    @Autowired
    public CategoryController(CategoryService categoryService, QuestionService questionService) {
        this.categoryService = categoryService;
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCategories() {
        List<String> all = categoryService.getAll();
        return new ResponseEntity(all, HttpStatus.OK);
    }

    @GetMapping("{category}/questions")
    public ResponseEntity<Page<QuestionDTO>> getQuestionsFor(@PathVariable String category, Pageable pagable) {
        Page<QuestionDTO> all = questionService.getFor(category, pagable);
        return new ResponseEntity(all, HttpStatus.OK);
    }
}

