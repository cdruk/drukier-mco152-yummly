package drukier.recipes;

import java.util.Comparator;
import java.util.Optional;

import javax.swing.text.JTextComponent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeController {

	private RecipeView view;
	private RecipeService service;
	private String url;

	public RecipeController(RecipeView view, RecipeService service) {
		this.view = view;
		this.service = service;
	}

	private void requestRecipeFeed(Call<RecipeFeedModel> call, JTextComponent matches) {

		call.enqueue(new Callback<RecipeFeedModel>() {

			@Override
			public void onResponse(Call<RecipeFeedModel> call, Response<RecipeFeedModel> response) {
				RecipeFeedModel feed = response.body();

				showResults(feed, matches);

			}

			@Override
			public void onFailure(Call<RecipeFeedModel> call, Throwable t) {
				t.printStackTrace();
			}

		});
	}

	protected void requestRecipeDetailsFeed(Call<RecipeModel> call) {

		call.enqueue(new Callback<RecipeModel>() {

			@Override
			public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
				RecipeModel feed = response.body();

				showRecipeUrl(feed);

			}

			@Override
			public void onFailure(Call<RecipeModel> call, Throwable t) {
				t.printStackTrace();
			}

		});
	}

	void searchRecipe() {
		requestRecipeFeed(service.getAllRecipes(searchRequest()), view.getResults());

	}

	private String searchRequest() {
		String query = view.getSearchValue().getText();
		return query.replaceAll(" ", "+");

	}

	protected void  showRecipeUrl(RecipeModel feed) {
		url = String.valueOf(feed.getAttribution().getUrl());
	}

	private void showResults(RecipeFeedModel feed, JTextComponent matches) {
		Optional<Recipe> best = feed.getMatches().stream().max(Comparator.comparing(e -> e.getRating()));

		String recipeName = String.valueOf(best.get().getRecipeName());

		String recipeId = String.valueOf(best.get().getId());

		requestRecipeDetailsFeed(service.getRecipeDetails(recipeId));
		

		String finalResults = (recipeName + ": " + url);

		view.getResults().setText(finalResults);
	}

}
