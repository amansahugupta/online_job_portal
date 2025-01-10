package com.aman.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobService {
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;

    @Autowired
    public JobService(JobRepository jobRepository, EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
    }

    public Job postJob(JobDTO jobDTO, Long employerId) {
        Employer employer = employerRepository.findById(employerId)
            .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setType(jobDTO.getType());
        job.setSalary(jobDTO.getSalary());
        job.setRequirements(jobDTO.getRequirements());
        job.setBenefits(jobDTO.getBenefits());
        job.setEmployer(employer);
        job.setStatus(JobStatus.ACTIVE);
        
        return jobRepository.save(job);
    }

    public Page<Job> searchJobs(JobSearchCriteria criteria, Pageable pageable) {
        // ...existing code...
    }

    // ...existing code...
}
