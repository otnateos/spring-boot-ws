package com.test.jobfinder.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class ModelTest {

    Logger log = LoggerFactory.getLogger(ModelTest.class);
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testTransformJobs()
            throws Exception
    {
        InputStream jobsJson = Test.class.getResourceAsStream("/jobs.json");
        List<Job> jobs = mapper.readValue(jobsJson, new TypeReference<List<Job>>(){});
        jobs.forEach(job -> log.info("Job Id: {}", job.getJobId()));
    }

    @Test
    public void testTransformWorkers()
            throws Exception
    {
        InputStream workersJson = getClass().getResourceAsStream("/workers.json");
        List<Worker> workers = mapper.readValue(workersJson, new TypeReference<List<Worker>>(){});
        workers.forEach(worker -> log.info("Worker Id: {}", worker.getUserId()));
    }
}