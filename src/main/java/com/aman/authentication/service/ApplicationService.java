package com.aman.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                            JobRepository jobRepository,
                            UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public Application submitApplication(ApplicationDTO applicationDTO, Long jobId, Long userId) {
        Job job = jobRepository.findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Application application = new Application();
        application.setJob(job);
        application.setApplicant(user);
        application.setStatus(ApplicationStatus.NEW);
        application.setCoverLetter(applicationDTO.getCoverLetter());
        application.setResumeUrl(applicationDTO.getResumeUrl());
        
        return applicationRepository.save(application);
    }

    public void updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
            .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        application.setStatus(status);
        applicationRepository.save(application);
    }

    // ...existing code...
}
