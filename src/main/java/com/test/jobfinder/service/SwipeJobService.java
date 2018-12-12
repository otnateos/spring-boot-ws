package com.test.jobfinder.service;

import com.test.jobfinder.comparator.HighestBillRateJobComparator;
import com.test.jobfinder.comparator.ImmediateJobComparator;
import com.test.jobfinder.comparator.MatchingWorkerSkillComparator;
import com.test.jobfinder.model.Job;
import com.test.jobfinder.model.Jobs;
import com.test.jobfinder.model.Worker;
import com.test.jobfinder.predicate.JobMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwipeJobService implements JobService {
    private final Logger log = LoggerFactory.getLogger(SwipeWorkerService.class);
    private final String SWIPE_ENDPOINT = "http://test.swipejobs.com/api/jobs";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Job> getJobs() {
        log.info("Retrieving jobs from {}", getClass());
        Jobs jobs = restTemplate.getForObject(SWIPE_ENDPOINT, Jobs.class);
        log.info("Found {} jobs from service", jobs.size());
        return jobs;
    }

    @Override
    public List<Job> findMatchingJobsForWorker(Worker worker, int maxMatch) {
        log.info("Finding matching jobs for worker Id: {} with max match of: {}", worker.getUserId(), maxMatch);
        return getJobs().stream().filter(new JobMatcher(worker))
                .sorted(new MatchingWorkerSkillComparator(new HashSet<>(worker.getSkills()))
                        .thenComparing(new HighestBillRateJobComparator()
                        .thenComparing(new ImmediateJobComparator())))
                .limit(maxMatch)
                .collect(Collectors.toList());
    }

}
