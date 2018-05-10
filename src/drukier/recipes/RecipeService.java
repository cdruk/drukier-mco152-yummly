package drukier.recipes;

import retrofit2.Call;
import retrofit2.http.GET;
//import retrofit2.http.Headers;

public interface RecipeService {

	//@Headers({"X-Yummly-App-ID:c12793b5","X-Yummly-App-Key: d946a1a08cac90f10c834351a99717de"})
	@GET("recipes?_app_id=c12793b5&_app_key=d946a1a08cac90f10c834351a99717de")
	Call<RecipeFeedModel> getAllRecipes();

}
