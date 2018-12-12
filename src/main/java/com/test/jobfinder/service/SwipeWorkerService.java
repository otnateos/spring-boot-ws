package com.test.jobfinder.service;

import com.test.jobfinder.exception.WorkerNotFoundException;
import com.test.jobfinder.model.Worker;
import com.test.jobfinder.model.Workers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SwipeWorkerService implements WorkerService {
    private final Logger log = LoggerFactory.getLogger(SwipeWorkerService.class);
    private final String SWIPE_ENDPOINT = "http://test.swipejobs.com/api/workers";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Worker> getWorkers() {
        log.info("Retrieving workers from {}", getClass());
        Workers workers = restTemplate.getForObject(SWIPE_ENDPOINT, Workers.class);
        log.info("Found {} workers from service", workers.size());
        return workers;
    }

    @Override
    public Worker findWorkerById(int workerId) throws WorkerNotFoundException {
        log.info("Finding worker by Id: {}", workerId);
        return getWorkers().stream().filter(worker -> worker.getUserId() == workerId).findFirst()
                .orElseThrow(()-> new WorkerNotFoundException(workerId));
    }
}
