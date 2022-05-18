package com.mindex.challenge.data;

/**
 * @author anshu on 17-05-2022
 * com.mindex.challenge.data
 **/
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;


    public ReportingStructure(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
