package com.example.project3.controllers;

import com.example.project3.entities.Privacy;
import com.example.project3.services.PrivacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrivacyController {

    @Autowired
    private PrivacyService service;

    @GetMapping("/privacy")
    public List<Privacy> getPrivacies() {
        return service.getAllPrivacy();
    }

    @GetMapping("/privacy/{id}")
    public Privacy getPrivacyById(@PathVariable("id") long id) {
        return service.getPrivacyById(id);
    }
}