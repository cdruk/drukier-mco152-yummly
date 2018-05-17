package drukier.recipes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRetroFitClient {
	
	Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.yummly.com/v1/api/")
			.addConverterFactory(GsonConverterFactory.create()).build();

	RecipeService service = retrofit.create(RecipeService.class);

	Call<RecipeFeedModel> mCall = service.getAllRecipes(null);

}
