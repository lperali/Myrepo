package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import javax.annotation.Resource;

@Path("/employee")
public class EmployeeInfo {

    /* To get the list of employeedetails */

    /* @Resource(name = "jdbc/UsersDB")
       private DataSource dataSource;*/

    @GET
        @Path("/list")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Employee> getEmpList() {

            Statement stmt = null;
            Connection conn = null;

            List<Employee> empList = new ArrayList<Employee>();

            try{
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
                conn = ds.getConnection();
                /*Class.forName("com.mysql.jdbc.Driver");
                  conn = DriverManager.getConnection("jdbc:mysql://localhost/employee","root","mouni777");*/

                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM employeedetails";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    Employee emp = new Employee();

                    emp.setEmpId(rs.getInt("id"));
                    emp.setEmpName(rs.getString("name"));
                    emp.setEmpAge(rs.getInt("age"));
                    emp.setEmpRole(rs.getString("role"));
                    emp.setEmpSal(rs.getInt("salary"));
                    emp.setEmpEmail(rs.getString("email"));
                    empList.add(emp);
                }

                rs.close();
            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            return empList;
        }

    /* To find the details of a particular employee */

    @GET
        @Path("/empid")

        @Produces(MediaType.APPLICATION_JSON)
        public Employee getInfo() {

            Connection conn = null;
            Statement stmt = null;

            Employee emp = new Employee();

            try{
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
                conn = ds.getConnection();
                String sql;
                sql = "SELECT * FROM employeedetails WHERE id=101";
                stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(sql);
                rs.next();

                emp.setEmpId(rs.getInt("id"));
                emp.setEmpName(rs.getString("name"));
                emp.setEmpAge(rs.getInt("age"));
                emp.setEmpRole(rs.getString("role"));
                emp.setEmpSal(rs.getInt("salary"));
                emp.setEmpEmail(rs.getString("email"));

                rs.close();
            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            return emp;
        }

    /* To insert the details of an employee */

    @POST
        @Path("/insert")
        @Produces(MediaType.TEXT_PLAIN)
        @Consumes(MediaType.APPLICATION_JSON)
        public String InsertInfo(Employee emp) {

            Connection conn = null;
            PreparedStatement stmt = null;


            String output = "Output:";

            try{
                //output = output + " inside try";
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
                conn = ds.getConnection();

                /*Class.forName("com.mysql.jdbc.Driver");
                  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "mouni777");*/
                String sql;
                sql = "INSERT INTO employeedetails VALUES(?,?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1,emp.getEmpId());
                stmt.setString(2, emp.getEmpName());
                stmt.setInt(3, emp.getEmpAge());
                stmt.setString(4, emp.getEmpRole());
                stmt.setInt(5, emp.getEmpSal());
                stmt.setString(6, emp.getEmpEmail());

                output = "Updated " + stmt.executeUpdate() + " Records Successfully";

            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }

            return output;
        }


    /* To update the details of an employee */

    @POST
        @Path("/update")
        @Produces(MediaType.TEXT_PLAIN)
        @Consumes(MediaType.APPLICATION_JSON)
        public String UpdateInfo(Employee emp) {

            Connection conn = null;
            PreparedStatement stmt = null;

            String output = null;

            try{

                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
                conn = ds.getConnection();

                String sql;
                sql = "UPDATE employeedetails SET salary=? WHERE id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,emp.getEmpSal());
                stmt.setInt(2, emp.getEmpId());


                output = "Updated " + stmt.executeUpdate() + " Records Successfully";
            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            return output;
        }

    /* To delete the employee information*/

    @POST
        @Path("/delete")
        @Produces(MediaType.TEXT_PLAIN)
        @Consumes(MediaType.APPLICATION_JSON)
        public String DeleteInfo(Employee emp) {

            Connection conn = null;
            PreparedStatement stmt = null;

            String output = null;

            try{
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
                conn = ds.getConnection();


                String sql;
                sql = "DELETE FROM employeedetails WHERE id=?";
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, emp.getEmpId());

                output = "Updated " + stmt.executeUpdate() + " Records Successfully";
            } catch(SQLException se){
                se.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            return output;
        }
}
