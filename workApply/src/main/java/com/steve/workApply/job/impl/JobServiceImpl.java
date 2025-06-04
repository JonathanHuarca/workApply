package com.steve.workApply.job.impl;

import com.steve.workApply.job.Job;
import com.steve.workApply.job.JobRepository;
import com.steve.workApply.job.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
