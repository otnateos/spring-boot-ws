package com.test.jobfinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSearchAddress {
    private String unit;
    private Integer maxJobDistance;
    private String longitude;
    private String latitude;
}
