package com.test.jobfinder.api;

import com.test.jobfinder.exception.WorkerNotFoundException;
import com.test.jobfinder.model.Job;
import com.test.jobfinder.model.Worker;
import com.test.jobfinder.service.JobService;
import com.test.jobfinder.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class JobFinderController {
    private static final Logger log = LoggerFactory.getLogger(JobFinderController.class);
    private static final int TOP_MAX = 3;

    @Autowired
    private WorkerService workerService;
    @Autowired
    private JobService jobService;

    public JobFinderController() {
        log.info("Initialising {}", getClass());
    }

    @GetMapping(value = "/worker/{workerId}")
    public List<Job> findTopJobMatchForWorker(@PathVariable long workerId) throws WorkerNotFoundException
    {
        log.info("Finding top {} job match for worker Id: {}", TOP_MAX, workerId);
        Worker worker = workerService.findWorkerById((int)workerId);
        log.info("Found worker {}", worker.getGuid());
        List<Job> jobs = jobService.findMatchingJobsForWorker(worker, TOP_MAX);
        log.info("Found {} matching jobs", jobs);
        return jobs;

    }

}
