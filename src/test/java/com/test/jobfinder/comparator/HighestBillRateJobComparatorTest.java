package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;
import org.junit.Test;

import static org.junit.Assert.*;

public class HighestBillRateJobComparatorTest {

    HighestBillRateJobComparator comparator = new HighestBillRateJobComparator();

    @Test
    public void compare() {
        assertEquals(0, comparator.compare(jobWithBillRate("$10"), jobWithBillRate("$10")));
        assertTrue(comparator.compare(jobWithBillRate("$10"), jobWithBillRate("$9")) < 0);
        assertTrue(comparator.compare(jobWithBillRate("$10"), jobWithBillRate("$11")) > 0);
        assertTrue(comparator.compare(jobWithBillRate("$10.01"), jobWithBillRate("$10.02")) > 0);
    }

    @Test
    public void compare_withInvalidValues() {
        assertTrue(comparator.compare(jobWithBillRate("$10"), jobWithBillRate(null)) < 0);
        assertTrue(comparator.compare(jobWithBillRate(null), jobWithBillRate("$11")) > 0);
        assertEquals(0, comparator.compare(jobWithBillRate(null), jobWithBillRate(null)));
    }

    Job jobWithBillRate(String billRate) {
        Job job = new Job();
        job.setBillRate(billRate);
        return job;
    }

}