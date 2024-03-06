package com.vicky.microservices.job;

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();
    void createJob(Job job);
    Job findJobByID(Long jobId);
    boolean deleteJobByID(Long jobId);
    boolean updateJobById(Long jobId, Job updatedJob);
}
