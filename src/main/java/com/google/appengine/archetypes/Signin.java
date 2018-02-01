package com.google.appengine.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.api.xdevapi.Result;
@WebServlet(urlPatterns = { "/signin" })

public class Signin extends HttpServlet{
	
	DbConnection monitor = new DbConnection();
	Profile p;
	Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		resp.setStatus(404);
	}
	
	private static final Logger log = Logger.getLogger(Signin.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		p = new Profile();
		StringBuilder sb = new StringBuilder();
		System.out.println(req.getContentType());

		// Have to use inputStreamReader to read over JSON.
		try {
		StringBuffer jb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
		
		// del POST viene user-email

		//System.out.println("HTTP string:"+ sb.toString());
		

		
		LoginInfo dataHttp = gson.fromJson(sb.toString(), LoginInfo.class);
		
		
		
		
		try {
			
			/*
			if(signin(dataHttp.getUser(), dataHttp.getPassword())) {
				
				
				
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/plain");
				resp.setStatus(200);
				pw.println("LoggedIn: True");
		
				
			}else {
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/plain");
				resp.setStatus(200);
				
				pw.println("LoggedIn: False");
				*/
			
			if(monitor.userLogin(dataHttp.getUser(), dataHttp.getPassword())) {
				
				p=gson.fromJson(monitor.userData(dataHttp.getUser()),Profile.class);
				LoginInfo l = new LoginInfo();
				l.setRecipe_id(p.getRecipeIds());
				
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/plain");
				resp.setStatus(200);
				pw.println("LoggedIn: True +");
				pw.println(l.toString());
				
			}else {
				PrintWriter pw = resp.getWriter();
				resp.setContentType("text/plain");
				resp.setStatus(200);
				
				pw.println("LoggedIn: False");}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}catch(IOException e) {
			Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, e);
			
		}
		
		
		
	}
	
	public boolean signin(String userhttp, String passwordhttp) throws SQLException{

		if(monitor.userExists(userhttp)){
			
			//System.out.println("password Db: "+uPassword(userhttp));
			//System.out.println("passwordhttp: "+passwordhttp);
			if(uPassword(userhttp).equals(passwordhttp.trim())) {

				System.out.println("Login Accepted");
				
				return true;
				
			} else {
				System.out.println("PASSWORD: INCORRECT");
				return false;
			}
			
		}else {
			System.out.println("Incorrect login credentials");
			return false;
		}
		

	}
	
	public String uPassword(String user) throws SQLException {
		
		Connection con =monitor.sync();
        boolean match;
        String password = "";
        
        String selectUser = "select password from profile where user= ?";
        PreparedStatement consulta = con.prepareStatement(selectUser);
        consulta.setString(1, user);
        ResultSet resultado = consulta.executeQuery();

        // iteracion para obtener resultados:
        if (resultado.next()) {

            password = resultado.getString("password");

        }

        resultado.close();
        consulta.close();
        
        monitor.syncOff();
		return password.trim();
		
		
	}
	
	 public static class LoginInfo {

	        private String user;
	        private String password;
	        private String recipe_id;

	        public LoginInfo(String user, String password) {
	            this.user = user;
	            this.password = password;
	        }
	        

	        public String getRecipe_id() {
				return recipe_id;
			}



			public void setRecipe_id(String recipe_id) {
				this.recipe_id = recipe_id;
			}



			public LoginInfo(){

	        }

	        public String getUser() {
	            return user;
	        }

	        public void setUser(String user) {
	            this.user = user;
	        }

	        public String getPassword() {
	            return password;
	        }

	        public void setPassword(String password) {
	            this.password = password;
	        }
	        
	        @Override
	        public String toString() {
	        // TODO Auto-generated method stub
	        Gson gson = new Gson();
	        	
	        return gson.toJson(this);
	        }
	    }
	
	

}

