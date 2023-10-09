package com.infinite.login;

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infinite.datasource.HikariCPTest;



 

@Controller
public class Login {
	Connection on=null;
	PreparedStatement ps=null;
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String date=request.getParameter("date");
		String gender=request.getParameter("gender");
		String profession=request.getParameter("profession");
		String marry=request.getParameter("married");
		String note=request.getParameter("note");

		try{
		DataSource datasource=HikariCPTest.getDataSource();
		on=datasource.getConnection();
		ps=on.prepareStatement("insert into register values(?,?,?,?,?,?,?,?)");
		ps.setString(1,name);
		ps.setString(2,email);
		ps.setString(3,pass);
		ps.setString(4,date);
		ps.setString(5,gender);
		ps.setString(6,profession);
		ps.setString(7,marry);
		ps.setString(8,note);
		System.out.println("it is here");
		ps.executeUpdate();
		return "inserted";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			try{
				on.rollback();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "failure";
	}

 

	}