package drukier.recipes;

import java.util.List;

public class RecipeFeedModel {

	private String type;
	private List<Recipe> matches;

	public RecipeFeedModel(String type, List<Recipe> matches) {
		this.type = type;
		this.matches = matches;
	}

	public String getType() {
		return type;
	}

	public List<Recipe> getMatches() {
		return matches;
	}
}
