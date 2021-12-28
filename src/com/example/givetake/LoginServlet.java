package com.example.givetake;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM users where username='"+username+"' and password='"+password+"'");

            if(rs.next()){
                response.sendRedirect("Home.html");
            }else{
                out.println("le mot de passe ou le nom d'utilisateur n'est pas valide...");
            }

            con.close();

        }catch (Exception e){

        }
    }
}
