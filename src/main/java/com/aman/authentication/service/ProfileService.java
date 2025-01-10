package com.aman.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService {
    private final JobSeekerRepository jobSeekerRepository;
    private final EmployerRepository employerRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public ProfileService(JobSeekerRepository jobSeekerRepository,
                         EmployerRepository employerRepository,
                         FileStorageService fileStorageService) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.employerRepository = employerRepository;
        this.fileStorageService = fileStorageService;
    }

    public JobSeekerProfile updateJobSeekerProfile(JobSeekerProfileDTO profileDTO, Long userId) {
        JobSeekerProfile profile = jobSeekerRepository.findByUserId(userId)
            .orElse(new JobSeekerProfile());
        
        profile.setFullName(profileDTO.getFullName());
        profile.setHeadline(profileDTO.getHeadline());
        profile.setLocation(profileDTO.getLocation());
        profile.setExperience(profileDTO.getExperience());
        profile.setSkills(profileDTO.getSkills());
        
        if (profileDTO.getPhotoFile() != null) {
            String photoUrl = fileStorageService.storeFile(profileDTO.getPhotoFile());
            profile.setPhotoUrl(photoUrl);
        }
        
        return jobSeekerRepository.save(profile);
    }

    public EmployerProfile updateEmployerProfile(EmployerProfileDTO profileDTO, Long userId) {
        // ...existing code...
    }

    // ...existing code...
}
