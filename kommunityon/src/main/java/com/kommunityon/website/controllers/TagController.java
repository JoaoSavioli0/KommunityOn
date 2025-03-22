package com.kommunityon.website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kommunityon.website.entities.Tag;
import com.kommunityon.website.repositories.TagRepository;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @PostMapping("/all")
    ResponseEntity<List<Tag>> allTags(){
        return ResponseEntity.ok(tagRepository.findAll());
    }
}
