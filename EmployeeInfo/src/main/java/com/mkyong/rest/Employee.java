package com.mkyong.rest;

//bean class
public class Employee {
    private int E_ID;
    private String E_NAME;
    private int DEPT_ID;
    private float SALARY;

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
    public float getSALARY() {
        return this.SALARY;
    }
    public void setSALARY(Float SALARY) {
        this.SALARY=SALARY;
    }
}
