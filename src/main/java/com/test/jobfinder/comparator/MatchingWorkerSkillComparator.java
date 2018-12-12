package com.test.jobfinder.comparator;

import com.test.jobfinder.model.Job;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Put the job that match worker's skill set first.
 */
public class MatchingWorkerSkillComparator implements Comparator<Job> {
    private final Set<String> workerSkills;

    public MatchingWorkerSkillComparator(Set<String> workerSkills) {
        this.workerSkills = Objects.isNull(workerSkills) ? new HashSet<>() : workerSkills;
    }

    private int getPreferredJobSkillScore(Job job) {
        return workerSkills.contains(job.getJobTitle()) ? -1 : 0;
    }

    @Override
    public int compare(Job j1, Job j2) {
        int pref1 = getPreferredJobSkillScore(j1);
        int pref2 = getPreferredJobSkillScore(j2);
        return Integer.compare(pref1, pref2);
    }
}
