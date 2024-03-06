package com.vicky.microservices.job.impl;

import com.vicky.microservices.job.Job;
import com.vicky.microservices.job.JobRepository;
import com.vicky.microservices.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobByID(Long jobID) {
        return jobRepository.findById(jobID).orElse(null);
    }

    @Override
    public boolean deleteJobByID(Long jobID) {
        try{
            if(jobRepository.existsById(jobID)){
                jobRepository.deleteById(jobID);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long jobId, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setTitle(updatedJob.getTitle());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
