package com.test.jobfinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Model for the worker.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Worker {
    private Integer rating;
    private Boolean isActive;
    private List<String> certificates = null;
    private List<String> skills = null;
    private JobSearchAddress jobSearchAddress;
    private String transportation;
    private Boolean hasDriversLicense;
    private List<Availability> availability = null;
    private String phone;
    private String email;
    private Name name;
    private Integer age;
    private String guid;
    private Integer userId;
}
