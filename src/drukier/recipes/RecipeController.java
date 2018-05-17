package drukier.recipes;

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

	private void requestRecipeFeed(Call<RecipeFeedModel> call, JTextComponent results) {

		call.enqueue(new Callback<RecipeFeedModel>() {

			@Override
			public void onResponse(Call<RecipeFeedModel> call, Response<RecipeFeedModel> response) {
				RecipeFeedModel feed = response.body();

				showResults(feed, results);
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
		String query = view.getSearchValue().toString();
		return query.replaceAll(" ", "+");

	}
	
	protected void showResults(RecipeFeedModel feed, JTextComponent results) {
		results.setText(view.getResults());
		
	}

}