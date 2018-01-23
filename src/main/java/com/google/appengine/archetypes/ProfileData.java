package com.google.appengine.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.google.appengine.archetypes.DbConnection;

/**
 * Servlet implementation class ProfileData
 */
@WebServlet("/profile_data")
public class ProfileData extends HttpServlet {

	DbConnection monitor = new DbConnection();
	String userDb = "";

	private static final Logger log  = Logger.getLogger(ProfileData.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder(br.readLine());
			String user = sb.toString();
			//System.out.println("User: "+user);
			
			userDb = monitor.userData(user);
			log.log(Level.INFO, userDb);

			response.getWriter().println(userDb);
			
			
		
		} catch (SQLException e) {
			Logger.getLogger(ProfileData.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(ProfileData.class.getName()).log(Level.SEVERE, null, e);
		}

	}
	
	

}
