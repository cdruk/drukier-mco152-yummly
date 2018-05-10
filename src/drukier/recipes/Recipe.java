package drukier.recipes;

public class Recipe {
	private String id;
	private RecipeProperties properties;
	
	public Recipe (String name, String[] ingredients, int rating)  {
		properties = new RecipeProperties(name, ingredients, rating);
	}

	public String getId() {
		return id;
	}

	public RecipeProperties getProperties() {
		return properties;
	}
}