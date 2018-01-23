package com.google.appengine.archetypes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mysql.cj.api.log.Log;

/**
 *
 * @author Miller
 */
public class DbConnection {
	private Connection con;

	/*
	 * Connection performed to mysql
	 */
	public Connection sync() {

		try {

			// Generate sql connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://35.226.202.177:3306/cappcakedb"; // Starts connection.
			String usr = "mgutierrez"; 
			String pass = "root"; 
			con = DriverManager.getConnection(url, usr, pass);
			
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;
	}

	public void syncOff() throws SQLException {
		con.close();

	}

	/*
	
	*/

	public String printAllUsers() throws SQLException, ClassNotFoundException {
		Connection con = sync();
		ArrayList<Profile> profileList = new ArrayList<>();
		String query = "select * from profile";
		Statement queryFinal = con.createStatement();
		ResultSet result = queryFinal.executeQuery(query);
		// Iterate results
		while (result.next()) {

			Profile p = new Profile();
			p.setName(result.getString("name"));
			p.setEmail(result.getString("email"));
			p.setPassword(result.getString("password"));
			p.setDateOfBirth(result.getString("date_birth"));
			p.setUser(result.getString("user"));
			p.setId(result.getInt("id"));

			// Add new object to list
			profileList.add(p);
		}

		String toJson = new Gson().toJson(profileList);
		syncOff();
		return toJson;

	}

	public void insertProfile(Profile p) throws SQLException {
		Connection con = sync();
		// ("user1", "user@hotmail.com","testingpass", "1990-12-21", "user3" )
		String insert = "INSERT INTO profile(name, email, password, date_birth, user) VALUES (?,?,?,?,?)";
		java.sql.PreparedStatement ps = con.prepareStatement(insert);
		
		ps.setString(1, p.getName());
		ps.setString(2, p.getEmail());
		ps.setString(3, p.getPassword());
		ps.setString(4, p.getDateOfBirth());
		ps.setString(5, p.getUser());
		
		ps.executeUpdate();
		ps.close();
		syncOff();
		
	}
	
	public boolean updateProfile(Profile user) throws SQLException {
		Connection con = sync();
		String query = "update profile set name=?, email=?, password=?, date_birth=? where user=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getDateOfBirth());
		ps.setString(5, user.getUser());
		ps.executeUpdate();
		
		
		String queryConfirm = "select * from profile where name=? AND email=? AND password=? AND date_birth=? AND user=?";
		PreparedStatement confirm = con.prepareStatement(queryConfirm);
		confirm.setString(1, user.getName());
		confirm.setString(2, user.getEmail());
		confirm.setString(3, user.getPassword());
		confirm.setString(4, user.getDateOfBirth());
		confirm.setString(5, user.getUser());
		ResultSet result = confirm.executeQuery();
		boolean exists = result.next();
		
		ps.close();
		result.close();
		confirm.close();
		syncOff();
		return exists;
		
	}

	public boolean userExists(String user) throws SQLException {
		Connection con = sync();
		String query = "select * from profile where user= ? ";
		PreparedStatement queryFinal = con.prepareStatement(query);
		queryFinal.setString(1, user.trim());
		ResultSet result = queryFinal.executeQuery();
		// Confirm we have results.
		boolean exists = result.next();
		result.close();
		queryFinal.close();
		syncOff();
		return exists;
	}
	
	public String userData(String user) throws SQLException{
		Logger.getLogger("user"+user);
		Connection con = sync();

		if(userExists(user)) {
			Profile p = new Profile();
			String query = "select * from profile where user= ? ";
			PreparedStatement queryFinal = con.prepareStatement(query);
			queryFinal.setString(1, user.trim());
			ResultSet result = queryFinal.executeQuery();
			
			if (result.next()) {

				p.setName(result.getString("name"));
				p.setEmail(result.getString("email"));
				p.setDateOfBirth(result.getString("date_birth"));
				p.setPassword(result.getString("password"));
				p.setUser(result.getString("user"));
				Logger.getLogger("Profile"+p.toString());
				syncOff();
				queryFinal.close();
				return p.toString();
				
	           
	        }
			
		}
		
		syncOff();
		return null;	
	}
	
	public boolean emailExists(String email) throws SQLException {
		Connection con = sync();
		String query = "select * from profile where email= ? ";
		PreparedStatement queryFinal = con.prepareStatement(query);
		queryFinal.setString(1, email.trim());
		ResultSet result = queryFinal.executeQuery();
		// Confirm emails exist
		boolean exists = result.next();
		result.close();
		queryFinal.close();
		syncOff();
		return exists;
	}

}
