package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author anshu on 17-05-2022
 * com.mindex.challenge.service.impl
 **/

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public ReportingStructure read(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            LOG.debug("Compensation invalid employeeId: [{}]", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId:" + id);
        }

        // creating a new reporting structure
        ReportingStructure reportingStructure = new ReportingStructure(employee);

        int numberOfReports = reportCount(employee);

        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;

    }

    /**
     * Calculate the number of reports for an employee and all of their distinct reports
     * It appears to be a Depth-First Search Traversal (DFS)
     * parameter: Employee
     * @return number of direct reports
     */
    private int reportCount(Employee employee){
        int numberOfReports = 0;

        List<Employee> employeeDirectReports = employee.getDirectReports();

        if (employeeDirectReports == null || employeeDirectReports.isEmpty()) {
            return numberOfReports;
        }
        for (Employee e : employeeDirectReports) {
            Employee employeeReport = employeeRepository.findByEmployeeId(e.getEmployeeId());
            numberOfReports++;
            numberOfReports += reportCount(employeeReport);
        }
        return numberOfReports;
    }

}
