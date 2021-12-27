package com.example.project3.services;

import com.example.project3.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    public Profile addProfile(Profile profile);
    public List<Profile> getAllProfiles();
    public Profile getProfileByUserId(long id);
    public void deleteProfile(long id);
    public Profile updateProfileByUserId(long id, Profile updateProfile);
}
