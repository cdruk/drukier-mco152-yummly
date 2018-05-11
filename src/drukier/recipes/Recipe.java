package drukier.recipes;

public class Recipe {
	private String recipeName;
	private String [] ingredients;
	private int rating;
	
	public Recipe(String recipeName, String[] ingredients, int rating) {

		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.rating = rating;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String[] getIngredients() {
		return ingredients;
	}

	public int getRating() {
		return rating;
	}
	
	
}