package drukier.recipes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import drukier.recipes.Recipe;
import drukier.recipes.RecipeFeedModel;
import drukier.recipes.RecipeService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceTest {

	@Test
	public void testGetAllRecipies() throws IOException {
		// given
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.yummly.com/v1/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		RecipeService service = retrofit.create(RecipeService.class);

		Call<RecipeFeedModel> call = service.getAllRecipes(null);

		// when
		Response<RecipeFeedModel> response = call.execute();

		// then
		assertNotNull(response.body());
		List<Recipe> details = response.body().getMatches();
		assertTrue(details.size() > 0);
		//String recipeName = details.get(0).getRecipeName();
		//assertNotNull(recipeName.getName());
	}
	
	@Test
	public void testGetRecipeDetails() throws IOException {
		// given
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.yummly.com/v1/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		RecipeService service = retrofit.create(RecipeService.class);

		Call<RecipeModel> call = service.getRecipeDetails("Lasagna-494544");

		// when
		Response<RecipeModel> response = call.execute();

		// then
		assertNotNull(response.body());
		Attribution details = response.body().getAttribution();
		assertTrue(details != null);
		//String recipeName = details.get(0).getRecipeName();
		//assertNotNull(recipeName.getName());
	}

}
