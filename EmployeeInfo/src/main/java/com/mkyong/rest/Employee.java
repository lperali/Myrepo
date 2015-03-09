package com.mkyong.rest;

public class Employee {
    int E_ID;
    String E_NAME;
    int DEPT_ID;
    String SALARY;
    public int getE_ID() {
        return this.E_ID;
    }
    public void setE_ID(int E_ID) {
        this.E_ID=E_ID;
    }
    public String getE_NAME() {
        return this.E_NAME;
    }
    public void setE_NAME(String E_NAME) {
        this.E_NAME=E_NAME;
    }
    public int getDEPT_ID() {
        return this.DEPT_ID;
    }
    public void setDEPT_ID(int DEPT_ID) {
        this.DEPT_ID=DEPT_ID;
    }
    public String getSALARY() {
        return this.SALARY;
    }
    public void setSALARY(String SALARY) {
        this.SALARY=SALARY;
    }
}
