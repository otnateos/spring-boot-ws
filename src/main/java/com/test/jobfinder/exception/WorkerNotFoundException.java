package com.test.jobfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkerNotFoundException extends Exception {
    public WorkerNotFoundException(int workerId) {
        this("Cannot find worker with Id: " + workerId);
    }
    public WorkerNotFoundException(String message) {
        super(message);
    }
}
