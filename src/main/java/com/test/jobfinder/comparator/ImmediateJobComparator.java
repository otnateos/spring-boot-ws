package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Comparing job based on the earliest availability.
 */
public class ImmediateJobComparator implements Comparator<Job> {
    private static final Logger log = LoggerFactory.getLogger(ImmediateJobComparator.class);
    private final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN);

    private Date getJobStartDate(Job job)
    {
        if (job.getStartDate() != null) {
            try {
                return DATE_FORMATTER.parse(job.getStartDate());
            }
            catch (ParseException e) {
                log.error("Failed to parse job start date {}", job.getStartDate());
            }
        }
        return null;
    }

    @Override
    public int compare(Job j1, Job j2) {
        Date start1 = getJobStartDate(j1);
        Date start2 = getJobStartDate(j2);
        if (ObjectUtils.nullSafeEquals(start1, start2))
            return 0;
        if (Objects.isNull(start1))
            return 1;
        if (Objects.isNull(start2))
            return -1;
        return start1.compareTo(start2);
    }
}
