package com.test.jobfinder.service;

import com.test.jobfinder.model.Job;
import com.test.jobfinder.model.Worker;

import java.util.List;

public interface JobService {
    List<Job> getJobs();

    /**
     * Find number of matching jobs for a worker.
     *
     * @param worker worker detail to be matched with the jobs
     * @param maxMatch maximum number of matching
     * @return list of jobs
     */
    List<Job> findMatchingJobsForWorker(Worker worker, int maxMatch);
}
