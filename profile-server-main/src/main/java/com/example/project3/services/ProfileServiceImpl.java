package com.example.project3.services;

import com.example.project3.entities.Profile;
import com.example.project3.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository repository;

    @Override
    public Profile addProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

    @Override
    public Profile getProfileByUserId(long id) {
        Optional<Profile> profile = repository.findById(id);
        return profile.get();
    }

    @Override
    public void deleteProfile(long id) {
        repository.deleteById(id);
    }

    @Override
    public Profile updateProfileByUserId(long id, Profile updateProfile) {
        Profile profileDB = repository.findById(id).get();
        profileDB.setPassword(updateProfile.getPassword());
        profileDB.setName(updateProfile.getName());
        profileDB.setAlias(updateProfile.getAlias());
        profileDB.setGender(updateProfile.getGender());
        profileDB.setDob(updateProfile.getDob());
        profileDB.setBio(updateProfile.getBio());
        profileDB.setProfilepic(updateProfile.getProfilepic());
        profileDB.setPrivacies(updateProfile.getPrivacies());
        repository.save(profileDB);
        return profileDB;
    }
}
