package com.test.jobfinder.service;

import com.test.jobfinder.exception.WorkerNotFoundException;
import com.test.jobfinder.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> getWorkers();
    Worker findWorkerById(int workerId) throws WorkerNotFoundException;
}
