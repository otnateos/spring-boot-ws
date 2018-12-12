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
public class JobServiceTest {
    private Logger log = LoggerFactory.getLogger(JobServiceTest.class);

    @Autowired
    private JobService jobService;

    @Test
    public void getJobs() {
        jobService.getJobs().forEach(job -> log.info("Job: {}", job.getJobTitle()));
    }
}
