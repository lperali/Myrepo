package com.training.myshop;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.*;


@Path("/category")
public class CategoryOperations {

    /*******************************************************
      Common Connector snippet
     ******************************************************/

    public Connection getConnected(){
        Connection conn = null;
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/MyShopDB");
            conn = ds.getConnection();
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**************************************************************
      To list the Categories
     **************************************************************/

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/list")
        public List<Category> categoryList() {
            Connection conn = null;
            List<Category> categoryList = new ArrayList<Category>();
            Statement stmt = null;
            try {
                conn = getConnected();
                stmt = conn.createStatement();
                String sql = "select * from category";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    Category category = new Category();
                    category.setCategoryId(rs.getInt("categoryId"));
                    category.setCategoryName(rs.getString("categoryName"));
                    category.setCategoryDescription(rs.getString("categoryDescription"));
                    //add this category object to the categoryList object
                    categoryList.add(category);
                }
                rs.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{if(conn!=null)
                    conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }

            //return Response.status(200).entity(output).build();
            return categoryList;
        }

    /**************************************************************
      To list the Categories by BrandId
     **************************************************************

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/list/{brand}")
        public List<Category> categoryListByBrand(@PathParam("brand") int brandId) {
            Connection conn = null;
            List<Category> categoryList = new ArrayList<Category>();
            Statement stmt = null;
            try {
                conn = getConnected();
                stmt = conn.createStatement();
                String sql = "select cat.categoryName,cat.categoryId,cat.categoryDescription from product prod, category cat where prod.categoryId = cat.categoryId AND prod.brandId = " + brandId;
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    Category category = new Category();
                    category.setCategoryId(rs.getInt("categoryId"));
                    category.setCategoryName(rs.getString("categoryName"));
                    category.setCategoryDescription(rs.getString("categoryDescription"));
                    //add this category object to the categoryList object
                    categoryList.add(category);
                }
                rs.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }// nothing we can do
                try{if(conn!=null)
                    conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }

            //return Response.status(200).entity(output).build();
            return categoryList;
        }

        /****************************************************************
          To update an existing Category
          ***************************************************************

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("/update")
        public String updateEmployee(@QueryParam("id") int id) {

            Connection conn = null;
            Statement stmt = null;
            Employee emp = null;
            String output = "Updated ";

            try{
                conn = getConnected();
                stmt = conn.createStatement();
                String sql = "update employee set address = 'address' where id = " + id;
                int updated = stmt.executeUpdate(sql);
                output = output + updated + "records successfully";
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if(stmt != null)
                        stmt.close();
                } catch(SQLException se2) {
                }// nothing we can do
                try {
                    if(conn != null)
                        conn.close();
                } catch(SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
            return output;
        }

        /****************************************************************
          To Delete an existing category
          ***************************************************************/

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        @Path("/delete")
        public String deleteCategory(@QueryParam("name") String categoryName) {

            Connection conn = null;
            PreparedStatement stmt = null;
            String output = "Deleted ";

            try{
                conn = getConnected();
                String sql = "delete from category where categoryName = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, categoryName);
                stmt.executeUpdate();
                output = output + " record with name" + categoryName + " successfully";
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if(stmt != null)
                        stmt.close();
                } catch(SQLException se2) {
                }// nothing we can do
                try {
                    if(conn != null)
                        conn.close();
                } catch(SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
            return output;
        }


        /****************************************************************
          To Insert a new Category record
          ***************************************************************/

        @POST
        @Produces(MediaType.TEXT_PLAIN)
        //@Consumes("application/json")
        @Path("/insert")
        public String insertCategory(
                @FormParam("categoryName") String categoryName,
                @FormParam("categoryDescription") String categoryDescription) {

            Connection conn = null;
            PreparedStatement pstmt = null;
            //Employee emp = null;
            String output = "Updated ";

            try{
                conn = getConnected();
                //stmt = conn.createStatement();
                output =categoryName;
                String sql = "insert into category(categoryName, categoryDescription) values(?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, categoryName);
                pstmt.setString(2, categoryDescription);
                int updated = pstmt.executeUpdate();
                output = output + updated + "records successfully";
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if(pstmt != null)
                        pstmt.close();
                } catch(SQLException se2) {
                }// nothing we can do
                try {
                    if(conn != null)
                        conn.close();
                } catch(SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
            return output;
        }
}
