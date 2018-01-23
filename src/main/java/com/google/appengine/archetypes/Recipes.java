package com.google.appengine.archetypes;

import com.google.cloud.datastore.Key;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miller
 */
public class Recipes {
    
    private String title;
    private String[] ingredientsBiscuit;
    private String[] ingredientsFrosting;
    private String[] ingredientsExtra;
    private String[] instructionsBiscuit;
    private String[] instructionsFrosting;
    private String[] instructionsExtra;
    private long id;
    private Long key;
    private String urlImage;
    private double ranking;
    private double ranking_count;

    public Recipes() {
    }
    
    
    

    public void setRanking(double ranking) {
		this.ranking = ranking;
	}
    
    

	public Long getKey() {
		return key;
	}




	public void setKey(Long key) {
		this.key = key;
	}




	public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] instructionsBiscuit, String[] instructionsFrosting, int id) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.id = id;
    }
    
    public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] instructionsBiscuit, String[] instructionsFrosting, int id, int ranking) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.id = id;
        this.ranking = ranking;
    }

    public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] ingredientExtra, String[] instructionsBiscuit, String[] instructionsFrosting, String[] insturctionsExtra, int id) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.ingredientsExtra = ingredientExtra;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.instructionsExtra = insturctionsExtra;
        this.id = id;
    }

    public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] ingredientExtra, String[] instructionsBiscuit, String[] instructionsFrosting, String[] insturctionsExtra) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.ingredientsExtra = ingredientExtra;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.instructionsExtra = insturctionsExtra;
    }

    public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] ingredientExtra, String[] instructionsBiscuit, String[] instructionsFrosting, int id, int ranking) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.ingredientsExtra = ingredientExtra;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.id = id;
        this.ranking = ranking;
    }

    public Recipes(String title, String[] ingredientsBiscuit, String[] ingredientsFrosting, String[] instructionsBiscuit, String[] instructionsFrosting, int id, String urlImage, int ranking) {
        this.title = title;
        this.ingredientsBiscuit = ingredientsBiscuit;
        this.ingredientsFrosting = ingredientsFrosting;
        this.instructionsBiscuit = instructionsBiscuit;
        this.instructionsFrosting = instructionsFrosting;
        this.id = id;
        this.urlImage = urlImage;
        this.ranking = ranking;
    }
    
    
    


	public String[] getIngredientsExtra() {
		return ingredientsExtra;
	}

	public void setIngredientsExtra(String[] ingredientsExtra) {
		this.ingredientsExtra = ingredientsExtra;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(long l) {
		this.ranking = l;
	}

	public String[] getInstructionsExtra() {
		return instructionsExtra;
	}

	public void setInstructionsExtra(String[] instructionsExtra) {
		this.instructionsExtra = instructionsExtra;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getIngredientsBiscuit() {
        return ingredientsBiscuit;
    }

    public void setIngredientsBiscuit(String[] ingredientsBiscuit) {
        this.ingredientsBiscuit = ingredientsBiscuit;
    }

    public String[] getIngredientsFrosting() {
        return ingredientsFrosting;
    }

    public void setIngredientsFrosting(String[] ingredientsFrosting) {
        this.ingredientsFrosting = ingredientsFrosting;
    }

    public String[] getIngredientExtra() {
        return ingredientsExtra;
    }

    public void setIngredientExtra(String[] ingredientExtra) {
        this.ingredientsExtra = ingredientExtra;
    }

    public String[] getInstructionsBiscuit() {
        return instructionsBiscuit;
    }

    public void setInstructionsBiscuit(String[] instructionsBiscuit) {
        this.instructionsBiscuit = instructionsBiscuit;
    }

    public String[] getInstructionsFrosting() {
        return instructionsFrosting;
    }

    public void setInstructionsFrosting(String[] instructionsFrosting) {
        this.instructionsFrosting = instructionsFrosting;
    }

    public String[] getInsturctionsExtra() {
        return instructionsExtra;
    }

    public void setInsturctionsExtra(String[] insturctionsExtra) {
        this.instructionsExtra = insturctionsExtra;
    }



    public void setId(int id) {
        this.id = id;
    }

	public double getRanking_count() {
		return ranking_count;
	}

	public void setRanking_count(double ranking_count) {
		this.ranking_count = ranking_count;
	}
    
}

