package com.test.jobfinder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerServiceTest {
    private Logger log = LoggerFactory.getLogger(WorkerServiceTest.class);

    @Autowired
    private WorkerService workerService;

    @Test
    public void getWorkers() {
        workerService.getWorkers().forEach(worker -> log.info("Worker: {}", worker.getName()));
    }

    @Test
    public void getWorkerById() throws Exception {
        log.info("Worker: {}", workerService.findWorkerById(0).getName());
    }
}
