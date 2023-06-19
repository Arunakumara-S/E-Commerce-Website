package com.pentagonespace;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body style='background-image: url(\"img/bulb.jpg\"); background-size: cover;'>");
		out.println("<a href='signup.html'>Add New Student</a>");
		out.println("<h1>PentagonSpace Student List </h1>");
		List<Student> list = StudentsDao.getAllStudents();
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Name</th><th>UserName</th><th>Email</th><th>Gender</th><th>Contry</th><th>Edit</th><th>Delete</th></tr>");
		
		for(Student s : list) {
			out.println("<tr><td>"+s.getName()+"</td><td>"+s.getUsername()+"</td><td>"+s.getCity()+"</td><td>"+s.getCountry()+"</td><td>"+s.getEmail()+"</td><td>"+s.getPassword()+"</td><td><a href='EditServlet?username="+s.getUsername()+"'>edit</a></td><td><a href='DeleteServlet?username="+s.getUsername()+"'>delete</a></td></tr>");	
		}
		
		out.print("</table>");
		out.println("</body>");
		out.close();
		
	}

}