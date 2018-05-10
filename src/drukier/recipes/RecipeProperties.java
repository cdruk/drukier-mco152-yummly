package drukier.recipes;

public class RecipeProperties {
	private String name;
	private String [] ingredients;
	private int rating;

	public RecipeProperties(String name, String[] ingredients, int rating) {
		this.name = name;
		this.ingredients = ingredients;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public String[] getIngredients() {
		return ingredients;
	}

	public int getRating() {
		return rating;
	}

	
}
