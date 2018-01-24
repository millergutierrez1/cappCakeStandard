package com.google.appengine.archetypes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.Logging;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;


@WebServlet(
	    name = "DatastoreRecipe",
	    urlPatterns = {"/recipes_list"}
	)

public class DatastoreRecipe extends HttpServlet{
	
	Recipes itmr;
	
	private static final Logger log = Logger.getLogger(DatastoreRecipe.class.getName());
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		super.doPost(req, resp);
		resp.setStatus(404);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
		ArrayList<Recipes> recipeList = new ArrayList<>();
		resp.setContentType("text/plain");
	    resp.setCharacterEncoding("UTF-8");
		
		String kind = "Recipes";
		Query<Entity> query = Query.newEntityQueryBuilder()
			    .setKind(kind).build();
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		QueryResults<Entity> recipesData = datastore.run(query);

		System.out.println(recipesData.hasNext());
		
		try {
			
			while(recipesData.hasNext()) {
				
				
				
				itmr = new  Recipes();
				
				
				Entity r = recipesData.next();
				Key itmrKey = r.getKey();
				itmr.setKey(itmrKey.getId());
				
				
				itmr.setTitle(r.getString("title"));
				itmr.setIngredientsBiscuit(r.getString("ingredientsBiscuit").split("\n"));
				//System.out.println("Ingredients Biscuit");
				/*for(int i = 0; i<itmr.getIngredientsBiscuit().length;i++) {
					System.out.println(itmr.getIngredientsBiscuit()[i]);
				} */
				//System.out.println();
				
				//System.out.println("ingredients Frosting:");
				itmr.setIngredientsFrosting(r.getString("ingredientsFrosting").split("\n"));
				//System.out.println("Frosting Ingredients:");
				/*for(int i = 0; i<itmr.getIngredientsFrosting().length;i++) {
					System.out.println(itmr.getIngredientsFrosting()[i]);
				} */
				//System.out.println();
				
				//System.out.println("Extra Ingredients");
				itmr.setIngredientsExtra(r.getString("ingredientsExtra").split("\n"));
				//System.out.println();
		
				itmr.setInstructionsBiscuit(r.getString("instructionsBiscuit").split("\n"));
				//System.out.println("Instructions Biscuit:");
				/*for(int i = 0; i<itmr.getInstructionsBiscuit().length;i++) {
					System.out.println(itmr.getInstructionsBiscuit()[i]);
				} */
				//System.out.println();
				
				itmr.setInstructionsFrosting(r.getString("instructionsFrosting").split("\n"));
				//System.out.println("Instructions Biscuit:");
				/*for(int i = 0; i<itmr.getInstructionsFrosting().length;i++) {
					System.out.println(itmr.getInstructionsFrosting()[i]);
				} */
				//System.out.println();
				
				itmr.setInstructionsExtra(r.getString("instructionsExtra").split("\n"));
				//System.out.println("Instructions Extra:");
				/*for(int i = 0; i<itmr.getInsturctionsExtra().length;i++) {
					System.out.println(itmr.getInstructionsExtra()[i]);
				} System.out.println();
				*/
				
				

				itmr.setId(r.getLong("id"));
				//System.out.println("id:"+itmr.getId());
			
				//System.out.println();
				itmr.setRanking(r.getDouble("ranking"));
				//System.out.println("Ranking: "+itmr.getRanking());
				
				//System.out.println();
				itmr.setUrlImage(r.getString("urlImage"));
				//System.out.println("urlImage: "+itmr.getUrlImage());
				
				//System.out.println();
				itmr.setRanking_count(r.getDouble("ranking_count"));
				//System.out.println("Ranking_count:"+itmr.getRanking_count());
				
				
				recipeList.add(itmr);
				log.log(Level.INFO, "RECIPE ADDED");

			}
					
		} catch(DatastoreException e) {
			log.log(Level.SEVERE, null, e);

		} catch(ClassCastException e) {
			log.log(Level.SEVERE, null, e);
			
		}
		
		Gson gson = new Gson();
		
		String recipesJSON = gson.toJson(recipeList);
		//System.out.println(recipesJSON);
		//System.out.println(recipesJSON.length());
		
		
		resp.getWriter().println(recipesJSON);
		log.log(Level.INFO, "RECIPES SENT");
		} catch (IOException e) {
			Logger.getLogger(DatastoreRecipe.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
		
		
	}
	
	
	

	
}
