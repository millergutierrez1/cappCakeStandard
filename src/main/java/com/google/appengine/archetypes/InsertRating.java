package com.google.appengine.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.search.query.ExpressionParser.negation_return;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.gson.Gson;

/**
 * Servlet implementation class InsertRating
 */
@WebServlet("/insert_rating")
public class InsertRating extends HttpServlet {

	Recipes r;
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(InsertRating.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setStatus(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder(br.readLine());
		Gson gson = new Gson();
		// System.out.println(sb.append(request.getInputStream()).toString());
		System.out.println("HTTPREQUEST" + sb.toString());
		r = gson.fromJson(sb.toString(), Recipes.class);
		log.log(Level.INFO, String
				.valueOf("Ranking: " + r.getRanking() / r.getRanking_count() + ". Votos: " + r.getRanking_count()));

		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		
		
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("Recipes");
		Key key = keyFactory.newKey(r.getKey());
		//Entity updatedRecipe = Entity.newBuilder(key).set("ranking", r.getRanking()).set("ranking_count", r.getRanking_count()).build();
		

		Entity test2 = Entity.newBuilder(datastore.get(key)).set("ranking", r.getRanking()).set("ranking_count", r.getRanking_count()).build();
		
		datastore.update(test2);
		
		
		/*DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		
		
		String recipekey = KeyFactory.createKeyString("Recipes", r.getKey());
		
		//System.out.println("ID: Recipe "+r.getId());
		
		Key key = KeyFactory.stringToKey(recipekey);
		
		Entity updatedRecipe;
		try {
			System.out.println(recipekey);
			updatedRecipe = datastore.get(key);
			updatedRecipe.setProperty("ranking", r.getRanking());
			updatedRecipe.setProperty("ranking_count", r.getRanking_count());

			// System.out.println(newRecipe);getKey().toString(

			Transaction txn = datastore.beginTransaction();
			try {

				datastore.put(txn, updatedRecipe);
				
				txn.commit();
				log.log(Level.INFO, "Commit Ranking Change");
			} finally {
				if (txn.isActive()) {
					log.log(Level.INFO, "RolledBack No changes in Datastore");
					txn.rollback();
				}

			}
		} catch (EntityNotFoundException e) {
			Logger.getLogger(InsertRating.class.getName()).log(Level.SEVERE, null, e);
		}*/

		response.setStatus(200);// new Entity("Recipes",key);

		// newRecipe.setProperty("title", r.getTitle());
		//
		// /*
		// * IngredientsBiscuit
		// */
		// StringBuilder sbp = new StringBuilder();
		// for(String list: r.getIngredientsBiscuit()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("ingredientsBiscuit", sbp.toString().trim());
		//
		// /*
		// * IngredientsFrosting
		// */
		// sbp = new StringBuilder();
		// for(String list:r.getIngredientsFrosting()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("ingredientsFrosting", sbp.toString().trim());
		//
		// /*
		// * IngredientExtra
		// */
		// sbp = new StringBuilder();
		// for(String list:r.getIngredientsExtra()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("ingredientsExtra", sbp.toString().trim());
		//
		// /*
		// * InstructionsBicuit
		// */
		// sbp = new StringBuilder();
		// for(String list:r.getInstructionsBiscuit()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("instructionsBiscuit", sbp.toString().trim());
		//
		// /*
		// * InsstructionsFrosting
		// */
		// sbp = new StringBuilder();
		// for(String list:r.getInstructionsFrosting()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("instructionsFrosting", sbp.toString().trim());
		//
		// /*
		// * InstructionsFrosting
		// */
		// sbp = new StringBuilder();
		// for(String list:r.getInstructionsExtra()) {
		// sbp.append(list+"\n");
		// }
		// newRecipe.setProperty("instructionsExtra", sbp.toString().trim());
		//
		// newRecipe.setProperty("id", r.getId());
		// newRecipe.setProperty("urlImage", r.getUrlImage());

	}

}
