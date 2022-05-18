package com.mindex.challenge.data;

import java.util.Collection;
import java.util.Date;

/**
 * @author anshu on 18-05-2022
 * com.mindex.challenge.data
 **/
public class Compensation {

    private String employeeId;
    private float salary;
    private Date effectiveDate;

    public Compensation(){
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


}
