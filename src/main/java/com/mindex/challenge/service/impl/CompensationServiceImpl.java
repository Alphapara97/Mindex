package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author anshu on 18-05-2022
 * com.mindex.challenge.service.impl
 **/
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {

        LOG.debug("Creating compensation:", compensation);

        String employeeId = compensation.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if(employee == null){
            LOG.info("Compensation not found for employeeid",employeeId);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"EmployeeId not found "+employeeId);
        }

        // if update is not permitted
        if(compensationRepository.findByEmployeeId(employeeId) != null){
            LOG.info("Compensation already presentfor employeeid",employeeId);

            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"EmployeeId already present"+employeeId);
        }

        //compensationRepository.save(compensation);

        compensationRepository.insert(compensation);
        return compensation;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employeeid:",employeeId );

        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
        if(compensation == null){
            LOG.info("Compensation not found for employeeid",employeeId);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"EmployeeId not found"+employeeId);
        }

        return compensation;
    }
}
