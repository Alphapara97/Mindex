package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * @author anshu on 18-05-2022
 * com.mindex.challenge.service
 **/
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String employeeId);
}
