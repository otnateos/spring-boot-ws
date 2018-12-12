package com.test.jobfinder.predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jobfinder.model.Job;
import com.test.jobfinder.model.Jobs;
import com.test.jobfinder.model.Workers;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class JobMatcherTest {

    JobMatcher matcher;
    Jobs allJobs;
    Workers workers;

    @Before
    public void setUp() throws Exception {
        InputStream jobsJson = getClass().getResourceAsStream("/jobs.json");
        this.allJobs = new ObjectMapper().readValue(jobsJson, Jobs.class);
        InputStream workersJson = getClass().getResourceAsStream("/workers.json");
        this.workers = new ObjectMapper().readValue(workersJson, Workers.class);
        this.matcher = new JobMatcher(workers.get(0));
    }

    @Test
    public void jobRequiresDriverLicense() {
        Job jobRequireDriverLicense = allJobs.get(1);
        assertFalse(matcher.test(jobRequireDriverLicense));
    }

    @Test
    public void jobsRequireCertificates() {
        assertFalse(matcher.test(allJobs.get(0)));
        assertFalse(matcher.test(allJobs.get(2)));
        assertFalse("Apparently the job is too far", matcher.test(allJobs.get(4)));
    }

    @Test
    public void match() {
        matcher = new JobMatcher(workers.get(1));
        assertTrue(matcher.test(allJobs.get(1)));
        assertFalse(matcher.test(allJobs.get(2)));
    }
}