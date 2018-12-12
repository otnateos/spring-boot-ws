package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmediateJobComparatorTest {
    ImmediateJobComparator comparator = new ImmediateJobComparator();
    @Test
    public void compare() {

        assertEquals(0, comparator.compare(jobWithDate("2015-11-09T01:32:28.29Z"), jobWithDate("2015-11-09T01:32:28.29Z")));
        assertTrue(comparator.compare(jobWithDate("2015-11-09T01:32:28.29Z"), jobWithDate("2015-11-02T22:12:40.263Z")) > 0);
        assertTrue(comparator.compare(jobWithDate("2015-11-09T01:32:28.29Z"), jobWithDate("2015-11-21T12:46:43.007Z")) < 0);
        assertTrue(comparator.compare(jobWithDate("2015-11-09T01:32:28.29Z"), jobWithDate("2015-11-09T01:32:28.32Z")) < 0);
    }

    @Test
    public void compare_withInvalidValues() {
        assertTrue(comparator.compare(jobWithDate("2015-11-09T01:32:28.29Z"), jobWithDate(null)) < 0);
        assertTrue(comparator.compare(jobWithDate(null), jobWithDate("2015-11-09T01:32:28.29Z")) > 0);
        assertEquals(0, comparator.compare(jobWithDate(null), jobWithDate(null)));
        assertEquals(0, comparator.compare(jobWithDate("2015-11-09"), jobWithDate(null)));
    }

    Job jobWithDate(String startDate) {
        Job job = new Job();
        job.setStartDate(startDate);
        return job;
    }
}