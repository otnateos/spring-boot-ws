package com.test.jobfinder.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Availability {
    private String title;
    private Integer dayIndex;
}
