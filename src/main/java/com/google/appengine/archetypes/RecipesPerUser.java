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

/**
 * Servlet implementation class RecipesPerUser
 */
@WebServlet("/recipes_per_user")
public class RecipesPerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DbConnection monitor = new DbConnection();
	private static final Logger log = Logger.getLogger(RecipesPerUser.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipesPerUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder(br.readLine());
		String recipes_list = "";
		String user = sb.toString();
		
		try {
			recipes_list = monitor.recipesPerUser(user.replace("\"", ""));
			
			if(recipes_list!="") {
				log.log(Level.INFO, recipes_list);
				response.setStatus(200);
				response.getWriter().println(recipes_list);
			} else if(recipes_list==null || recipes_list=="") {
				response.setStatus(200);
				response.getWriter().println("No recipes found");
			}
			
			
		} catch (SQLException e) {
			Logger.getLogger(RecipesPerUser.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		
	}

}
