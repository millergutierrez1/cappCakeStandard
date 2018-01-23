package com.google.appengine.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = { "/insert" })

public class Insert_profile extends HttpServlet {

	DbConnection monitor = new DbConnection();
	
	private static final Logger log = Logger.getLogger(Insert_profile.class.getName());
	
	/**
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);	
		resp.setStatus(404);	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {


		StringBuilder sb = new StringBuilder();
		//System.out.println(req.getContentType());

		// Have to use inputStreamReader to read over JSON.

		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();

		//Take from the post the email address

		//System.out.println("HTTP string:"+ sb.toString());
		if(sb.toString().contains("$**$")) {
			
			String[] postValues = sb.toString().split("\\$\\*\\*\\$");

			try {
				
				if (monitor.userExists(postValues[0])) {

					log.log(Level.INFO,"User exists");
					System.out.println("HTTP:200 --> User exists");
					resp.setContentType("text/plain");
					PrintWriter pw = resp.getWriter();
					pw.println("User exists");

				} else {
					//System.out.println("HTTP:200 --> User Doesn't exists");
					log.log(Level.INFO,"User Doesn't exists");
					resp.setContentType("text/plain");
					PrintWriter pw = resp.getWriter();
					pw.println("User Doesn't exists");

				}

				if (monitor.emailExists(postValues[1])) {
					log.log(Level.INFO,"Email exists");
					//System.out.println("HTTP:200 --> Email exists");
					resp.setContentType("text/plain");
					PrintWriter pw = resp.getWriter();
					pw.println("Email exists");

				} else {
					log.log(Level.INFO,"Email doesn't exist");
					//System.out.println("HTTP:200 --> Email Doesn't exists");
					resp.setContentType("text/plain");
					PrintWriter pw = resp.getWriter();
					pw.println("Email Doesn't exist");

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.log(Level.SEVERE,null,e);

			}
			
		} else {
			
			Gson json = new Gson();
			Profile p = json.fromJson(sb.toString(), Profile.class);
			//System.out.println(p);
			log.log(Level.INFO,"USER: "+p.getUser()+" "
					+ "EMAIL: "+p.getEmail()
					+ " DATE: "+p.getDateOfBirth()
					+ " NAME: "+p.getName());

			PrintWriter pw = resp.getWriter();
			try {
				monitor.insertProfile(p);
				resp.setContentType("text/plain");
					
				pw.println("User creation succesful");
				log.log(Level.INFO,"USER-CREATION-SUCCESFUL");


			} catch (SQLException e) {
				pw.println("User not created.");
				Logger.getLogger(Insert_profile.class.getName()).log(Level.SEVERE, null, e);
			}
			
		}
			
			
		}catch(IOException e) {
			Logger.getLogger(Insert_profile.class.getName()).log(Level.SEVERE, null, e);
		}
		
		

	}

}
