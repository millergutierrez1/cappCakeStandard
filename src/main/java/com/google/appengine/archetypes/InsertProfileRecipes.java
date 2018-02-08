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
import com.google.gson.JsonSyntaxException;

/**
 * Servlet implementation class InsertProfileRecipes
 */
@WebServlet("/insert_profile_recipes")
public class InsertProfileRecipes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(InsertProfileRecipes.class.getName());
	
	
	DbConnection monitor = new DbConnection();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProfileRecipes() {
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
		log.log(Level.INFO, sb.toString());
		SaveRecipeId userRecipe= new SaveRecipeId();
		userRecipe = gson.fromJson(sb.toString(), SaveRecipeId.class);
		
		
		
		
		try {
			Profile user = new Profile(); 
			user=gson.fromJson(monitor.userData(userRecipe.getName()),Profile.class);
			
			System.out.println(user);
			if(user.getRecipeIds()!=null ||user.getRecipeIds()=="") {
				user.setRecipeIds(user.getRecipeIds()+"-"+userRecipe.getId());
			} else {
				user.setRecipeIds(userRecipe.getId());
			}
			
			if(monitor.insertRecipeIds(user)) {
				response.setStatus(200);
				response.getWriter().println("recipeSaved");
			} else {
				response.setStatus(404);
				response.getWriter().println("User_not_Found");
			}
			
			
			
			
		} catch (JsonSyntaxException | SQLException e) {
			Logger.getLogger(InsertProfileRecipes.class.getName()).log(Level.SEVERE, null, e);
		}		
		
		
		
	}
	

    public class SaveRecipeId{
        String name;
        String id;

        public SaveRecipeId(){

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        
        @Override
        public String toString() {
        	// TODO Auto-generated method stub
        	return gson.toJson(super.toString());
        }
    }

}


