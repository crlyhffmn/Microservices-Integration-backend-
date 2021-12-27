package com.example.project3.services;

import com.example.project3.entities.Privacy;

import java.util.List;

public interface PrivacyService {
    public List<Privacy> getAllPrivacy();
    public Privacy getPrivacyById(long privacy_id);
}
