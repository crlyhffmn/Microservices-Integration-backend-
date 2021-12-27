package com.example.project3.controllers;

import com.example.project3.entities.Profile;
import com.example.project3.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    @PostMapping("/profiles")
    public Profile saveProfile(@RequestBody Profile profile) {
        return service.addProfile(profile);
    }

    @GetMapping("/profiles")
    public List<Profile> getProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping("/profiles/{id}")
    public Profile getProfileById(@PathVariable("id") long id) {
        return service.getProfileByUserId(id);
    }

    @DeleteMapping("/profiles/{id}")
    public String deleteProfile(@PathVariable("id") long id) {
        service.deleteProfile(id);
        return "Profile has been removed";
    }

    @PutMapping("/profiles/{id}")
    public String updateProfile(@PathVariable("id") long id, @RequestBody Profile profile) {
        service.updateProfileByUserId(id, profile);
        return "Profile successfully updated";
    }
}
