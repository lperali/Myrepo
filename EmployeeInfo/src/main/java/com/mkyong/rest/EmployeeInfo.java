package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.*;


@Path("/emp")
public class EmployeeInfo {
    @GET
    @Path("/getemp")
    @Produces("application/json")
    //************************ method to get the given employee details ****************************
    public Employee getEmp() {
        Employee emp = new Employee();
        emp.setE_ID(1010);
        String output="";
        int rows=0;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
            Connection conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement("select * from EMP where E_ID = ?");
            stmt.setInt(1,emp.getE_ID());
            ResultSet rs=stmt.executeQuery();
            while(rs.next()) {
                emp.setE_NAME(rs.getString(2));
                emp.setDEPT_ID(rs.getInt(3));
                emp.setSALARY(rs.getString(4));
            }
                conn.close();
            }catch(Exception e){
                output=e.toString();
            }
            return emp;
    }



    @GET
    @Path("/empdetails")
    @Produces("application/json")
    //**************************** method to get all employees data *****************************
    public Map getEmpDteails(){
        Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();
        String output="";
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
            Connection conn = ds.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from EMP");
            while(rs.next()){
                Employee emp = new Employee();
                emp.setE_ID(rs.getInt(1));
                emp.setE_NAME(rs.getString(2));
                emp.setDEPT_ID(rs.getInt(3));
                emp.setSALARY(rs.getString(4));
                empMap.put(emp.getE_ID(),emp);
            }
            conn.close();
        }catch(Exception e){
                System.out.println(e);
        }
        return empMap;
    }



    @GET
    @Path("/insertemp")
    @Produces("application/json")
    //***************************** method to insert an employee ***********************************
    public Employee getInsertedEmp(){
        Employee emp = new Employee();
        emp.setE_ID(1020);
        emp.setE_NAME("VARUN");
        emp.setDEPT_ID(10);
        emp.setSALARY("25000");
        String output="";
        int rows=0;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
            Connection conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement("insert into EMP values(?,?,?,?)");
            stmt.setInt(1,emp.getE_ID());
            stmt.setString(2,emp.getE_NAME());
            stmt.setInt(3,emp.getDEPT_ID());
            stmt.setString(4,emp.getSALARY());
            rows = stmt.executeUpdate();
            output = "Number of Employees inserted :" + rows;
            conn.close();
        }catch(Exception e){
            output=e.toString();
        }
        return emp;
    }



    @GET
    @Path("/deleteemp")
    @Produces("application/TEXT_PLAIN")
    //******************************* method to delete an employee*********************************
    public String getDeletedEmp(){
        String output="";
        int rows=0;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
            Connection conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement("delete from EMP where E_ID = ?");
            stmt.setInt(1,1016);
            rows = stmt.executeUpdate();
            output = "Number of Employees deleted :" + rows;
            conn.close();
        }catch(Exception e) {
            output=e.toString();
        }
        return output;
    }



    @GET
    @Path("/updateemp")
    @Produces("application/json")
    //******************************* method to update an employee ***********************************
    public Employee getUpdatedEmp(){
        Employee emp = new Employee();
        emp.setE_ID(1012);
        emp.setE_NAME("HARSHA");
        emp.setDEPT_ID(20);
        emp.setSALARY("23000");
        String output="";
        int rows=0;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
            Connection conn = ds.getConnection();
            PreparedStatement stmt=conn.prepareStatement("update EMP set E_NAME = ?, DEPT_ID = ?, SALARY = ? where E_ID = ?");
            stmt.setInt(4,emp.getE_ID());
            stmt.setString(1,emp.getE_NAME());
            stmt.setInt(2,emp.getDEPT_ID());
            stmt.setString(3,emp.getSALARY());
            rows = stmt.executeUpdate();
            output = "Number of Employees updated :" + rows;
            conn.close();
        }catch(Exception e) {
            output=e.toString();
        }
        return emp;
    }
}
