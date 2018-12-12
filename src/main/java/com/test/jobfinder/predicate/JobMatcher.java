package com.test.jobfinder.predicate;

import com.test.jobfinder.model.Job;
import com.test.jobfinder.model.JobSearchAddress;
import com.test.jobfinder.model.Location;
import com.test.jobfinder.model.Worker;

import java.util.function.Predicate;

/**
 * Matcher to match job against a worker.
 */
public class JobMatcher implements Predicate<Job>
{
    private final Worker worker;

    public JobMatcher(Worker worker) {
        this.worker = worker;
    }

    @Override
    public boolean test(Job job) {
        // make sure the job still require worker
        if (job.getWorkersRequired() <= 0)
            return false;
        // if driver license required, worker must have it
        if (job.getDriverLicenseRequired() && !worker.getHasDriversLicense())
            return false;
        // worker must match all required certificate
        if (!worker.getCertificates().containsAll(job.getRequiredCertificates()))
            return false;
        // worker is willing to travel to certain distance, any further job exclude
        return withinDistance(job.getLocation(), worker.getJobSearchAddress());
    }

    public boolean withinDistance(Location jobPos, JobSearchAddress workerPos) {
        // if worker does not specify distance
        if (workerPos.getMaxJobDistance() == null || workerPos.getMaxJobDistance() == 0)
            return true;
        int maxDistance = workerPos.getMaxJobDistance();
        if ("km".equalsIgnoreCase(workerPos.getUnit()))
            maxDistance *= 1000;
        double distance = distanceInMeters(
                Double.parseDouble(jobPos.getLatitude()), Double.parseDouble(workerPos.getLatitude()),
                Double.parseDouble(jobPos.getLongitude()), Double.parseDouble(workerPos.getLongitude()),
                0.0, 0.0);
        return (maxDistance - distance) > 0;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * Source:
     * - https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
     * - https://www.movable-type.co.uk/scripts/latlong.html
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @return Distance in Meters
     */
    public double distanceInMeters(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
