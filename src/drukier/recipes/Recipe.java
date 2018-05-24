package drukier.recipes;

public class Recipe {
	private String recipeName;
	private String id;
	private int rating;
	
	public Recipe(String recipeName, String id, int rating) {

		this.recipeName = recipeName;
		this.id = id;
		this.rating = rating;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}
	
	
}