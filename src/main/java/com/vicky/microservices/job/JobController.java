package com.vicky.microservices.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAllJobs(){
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    //@RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<Job> findJobByID(@PathVariable Long jobId){
        Job job = jobService.findJobByID(jobId);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobByID(@PathVariable Long jobId){
        boolean response = jobService.deleteJobByID(jobId);
        if(response){
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJobByID(@PathVariable Long jobId, @RequestBody Job updatedJob){
        boolean response = jobService.updateJobById(jobId, updatedJob);
        if(response){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
        }
    }

}
