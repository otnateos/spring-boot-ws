package com.test.jobfinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job {
    private Boolean driverLicenseRequired;
    private List<String> requiredCertificates = null;
    private Location location;
    private String billRate;
    private Integer workersRequired;
    private String startDate;
    private String about;
    private String jobTitle;
    private String company;
    private String guid;
    private Integer jobId;
}
