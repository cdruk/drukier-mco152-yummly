package drukier.recipes;

import java.util.List;

public class RecipeFeedModel {

	private List<Recipe> matches;
	private int totalMatchCount;

	public RecipeFeedModel(List<Recipe> matches, int totalMatchCount) {
		super();
		this.matches = matches;
		this.totalMatchCount = totalMatchCount;
	}

	public List<Recipe> getMatches() {
		return matches;
	}

	public int getTotalMatchCount() {
		return totalMatchCount;
	}

}
