package drukier.recipes;

public class RecipeModel {

	private Attribution attribution;
	private String[] ingredientLines;

	public RecipeModel(Attribution attribution, String[] ingredientLines) {
		super();
		this.attribution = attribution;
		this.ingredientLines = ingredientLines;
	}

	public Attribution getAttribution() {
		return attribution;
	}

	public String[] getIngredientLines() {
		return ingredientLines;
	}

}
