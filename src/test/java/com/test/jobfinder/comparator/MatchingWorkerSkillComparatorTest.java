package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.HashSet;

import static org.junit.Assert.*;

public class MatchingWorkerSkillComparatorTest {

    MatchingWorkerSkillComparator comparator;

    @Test
    public void compareForNonSkilledWorker() {
        this.comparator = new MatchingWorkerSkillComparator(new HashSet<>());
        assertEquals(0, comparator.compare(jobWithTitle("Engineer"), jobWithTitle("Programmer")));
        assertEquals(0, comparator.compare(jobWithTitle("Engineer"), jobWithTitle("Cook")));
        assertEquals(0, comparator.compare(jobWithTitle(null), jobWithTitle("Cook")));
    }

    @Test
    public void compare() {
        this.comparator = new MatchingWorkerSkillComparator(Sets.newSet("Business Analyst", "Software Architect"));
        assertEquals(0, comparator.compare(jobWithTitle("Engineer"), jobWithTitle("Programmer")));
        assertEquals(-1, comparator.compare(jobWithTitle("Software Architect"), jobWithTitle("Programmer")));
        assertEquals(0, comparator.compare(jobWithTitle("Software Architect"), jobWithTitle("Business Analyst")));
        assertEquals(1, comparator.compare(jobWithTitle("Cook"), jobWithTitle("Business Analyst")));
    }

    Job jobWithTitle(String title) {
        Job job = new Job();
        job.setJobTitle(title);
        return job;
    }
}