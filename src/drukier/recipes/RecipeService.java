package drukier.recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

	@GET("recipes?_app_id=c12793b5&_app_key=d946a1a08cac90f10c834351a99717de&q=")
	Call<RecipeFeedModel> getAllRecipes(@Query("q") String recipeQuery);

}
