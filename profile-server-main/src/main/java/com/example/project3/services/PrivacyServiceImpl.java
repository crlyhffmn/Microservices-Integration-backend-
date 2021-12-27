package com.example.project3.services;

import com.example.project3.entities.Privacy;
import com.example.project3.repositories.PrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivacyServiceImpl implements PrivacyService{

    @Autowired
    private PrivacyRepository repository;

    @Override
    public List<Privacy> getAllPrivacy() {
        return repository.findAll();
    }

    @Override
    public Privacy getPrivacyById(long id) {
        Optional<Privacy> privacy = repository.findById(id);
        return privacy.get();
    }

}