package com.test.jobfinder.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jobfinder.exception.WorkerNotFoundException;
import com.test.jobfinder.model.Jobs;
import com.test.jobfinder.model.Worker;
import com.test.jobfinder.service.JobService;
import com.test.jobfinder.service.WorkerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JobFinderController.class)
public class JobFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private JobFinderController jobFinderController;

    @MockBean
    private WorkerService workerService;
    @MockBean
    private JobService jobService;

    private Jobs allJobs;

    @Before
    public void setUp() throws Exception {
        InputStream jobsJson = Test.class.getResourceAsStream("/jobs.json");
        this.allJobs = new ObjectMapper().readValue(jobsJson, Jobs.class);
    }

    @Test
    public void findTopJobMatchForWorker_noWorkerFound() throws Exception {
        given(workerService.findWorkerById(0)).willThrow(new WorkerNotFoundException(0));
        mvc.perform(get("/match/worker/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findTopJobMatchForWorker() throws Exception {
        Jobs jobs = new Jobs();
        jobs.add(allJobs.get(5));
        jobs.add(allJobs.get(7));
        jobs.add(allJobs.get(8));
        Worker worker = new Worker();

        given(workerService.findWorkerById(0)).willReturn(worker);
        given(jobService.findMatchingJobsForWorker(worker, 3)).willReturn(jobs);

        mvc.perform(get("/match/worker/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].jobTitle", is(jobs.get(0).getJobTitle())));
    }
}