package com.luv2code.testdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "TestDbServlet", value = "/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // setup connection variables like username and password to connect to database
        String user = "springstudent";
        String password = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false";
        String driverClassName = "com.mysql.jdbc.Driver";

        // get a connection to the database
        try {
            PrintWriter out = response.getWriter();
            out.println("Hey I am connecting to database " + jdbcUrl);

            Class.forName(driverClassName);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);

            out.println("Connection Successful");

            myConn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

}
