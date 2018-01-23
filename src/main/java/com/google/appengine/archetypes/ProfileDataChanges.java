package com.google.appengine.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ProfileDataChanges
 */
@WebServlet("/profile_changes")
public class ProfileDataChanges extends HttpServlet {
	private static final Logger log = Logger.getLogger(ProfileDataChanges.class.getName());
	
	DbConnection monitor;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		monitor = new DbConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder(br.readLine());
		//System.out.println("UserChanges: "+sb.toString());
		
		Gson gson = new Gson();
		Profile user = gson.fromJson(sb.toString(), Profile.class);
		boolean changes = false;
		log.log(Level.INFO, "USER-CHANGES: "+user.getUser());

		try {
			if(monitor.updateProfile(user)) {
				
				response.setStatus(200);
				response.getWriter().println("Profile Updated");
				log.log(Level.INFO, "Profile Updated: user"+user.getUser());
				
			} else {
				response.setStatus(200);
				response.getWriter().println("Profile NOT updated");	
				log.log(Level.INFO, "Profile NOT Updated: user"+user.getUser());

			}
		} catch (SQLException e) {
			Logger.getLogger(ProfileDataChanges.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}
