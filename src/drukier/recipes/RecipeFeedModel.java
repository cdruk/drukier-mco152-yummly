package drukier.recipes;

import java.util.List;

public class RecipeFeedModel {

	private String type;
	private List<Recipe> details;

	public RecipeFeedModel(String type, List<Recipe> details) {
		this.type = type;
		this.details = details;
	}

	public String getType() {
		return type;
	}

	public List<Recipe> getDetails() {
		return details;
	}
}
