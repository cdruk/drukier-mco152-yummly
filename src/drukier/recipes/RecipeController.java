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



	void searchRecipe() {
		requestRecipeFeed(service.getAllRecipes(searchRequest()), view.getResults());

	}

	private String searchRequest() {
		String query = view.getSearchValue().getText();
		return query.replaceAll(" ", "+");

	}

	private void showResults(RecipeFeedModel feed, JTextComponent matches) {
		Optional<Recipe> best = feed.getMatches().stream().max(Comparator.comparing(e -> e.getRating()));

		String recipeName = best.get().getRecipeName();

		String recipeId = best.get().getId();

		service.getRecipeDetails(recipeId).enqueue(new Callback<RecipeModel>() {

			@Override
			public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
				showRecipeResponse(recipeName, response);
			}

			@Override
			public void onFailure(Call<RecipeModel> call, Throwable t) {
				t.printStackTrace();
			}

		});
		

	}

	private void showRecipeResponse(String recipeName, Response<RecipeModel> response) {
		RecipeModel feed = response.body();

		String url = feed.getAttribution().getUrl();
		
		String ingredients =  String.join("<br />", feed.getIngredientLines());
		
		String finalResults = (recipeName + ": " 
				+ "<a href=\"" + url + "\">" + url + "</a>"
				+ "<br />" + ingredients);

		view.getResults().setText(finalResults);
	}

}
