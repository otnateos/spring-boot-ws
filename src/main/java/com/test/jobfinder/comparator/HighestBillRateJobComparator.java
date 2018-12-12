package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

/**
 * Highest bill rate comparator based on US$.
 */
public class HighestBillRateJobComparator implements Comparator<Job>
{
    @Override
    public int compare(Job j1, Job j2) {
        if (ObjectUtils.nullSafeEquals(j1.getBillRate(), j2.getBillRate()))
            return 0;
        if (Objects.isNull(j1.getBillRate()))
            return 1;
        if (Objects.isNull(j2.getBillRate()))
            return -1;
        BigDecimal rate1 = new BigDecimal(j1.getBillRate().substring(1));
        BigDecimal rate2 = new BigDecimal(j2.getBillRate().substring(1));
        return rate2.compareTo(rate1);
    }
}
