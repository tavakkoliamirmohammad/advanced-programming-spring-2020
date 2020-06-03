package com.resturant.staffManagement;

import com.resturant.enums.Gender;
import com.resturant.exceptions.AlreadyFired;
import com.resturant.exceptions.InvalidNationalCodeException;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Staff extends Person{
    private BigDecimal income;
    private Date startDate;
    private Date resigningDate;

    public Staff(String name, String address, Date birthDate, String nationalCode, Gender gender, BigDecimal income, Date startDate) throws InvalidNationalCodeException {
        super(name, address, birthDate, nationalCode, gender);
        this.income = income;
        this.startDate = startDate;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getResigningDate() {
        return resigningDate;
    }

    public void fire(Date firingDate) throws AlreadyFired {
        if(resigningDate != null){
            throw new AlreadyFired();
        }
        else {
            this.resigningDate = firingDate;
        }
    }

}
