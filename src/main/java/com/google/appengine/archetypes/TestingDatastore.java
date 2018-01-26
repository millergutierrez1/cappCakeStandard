package com.google.appengine.archetypes;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Servlet implementation class TestingDatastore
 */
@WebServlet("/testing_datastore")
public class TestingDatastore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestingDatastore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//Filter queryFilter = new FilterPredicate("id", FilterOperator.EQUAL, r.getId());
//		
//		Query query = new Query("Recipes").setFilter(queryFilter);
//		Entity test = new Entity("Recipes");
//		test = datastore.prepare(query).asSingleEntity();
//		System.out.println(test.getKey());
//		System.out.println(test);
		
		 Entity employee = new Entity("Employee", "asalieri");
		    employee.setProperty("firstName", "Antonio");
		    employee.setProperty("lastName", "Salieri");
		    employee.setProperty("hireDate", new Date());
		    employee.setProperty("attendedHrTraining", true);

		    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		    datastore.put(employee);
		    // [END kind_example]

		    try {
				Entity got = datastore.get(employee.getKey());
				System.out.println(got.getKey());
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
