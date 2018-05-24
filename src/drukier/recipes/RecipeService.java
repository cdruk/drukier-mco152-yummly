package drukier.recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeService {

	@GET("recipes?_app_id=c12793b5&_app_key=d946a1a08cac90f10c834351a99717de")
	Call<RecipeFeedModel> getAllRecipes(@Query("q") String recipeQuery);

	@GET("recipe/{recipe-id}?_app_id=c12793b5&_app_key=d946a1a08cac90f10c834351a99717de")
	Call<RecipeModel> getRecipeDetails(@Path("recipe-id") String recipeId);
}
