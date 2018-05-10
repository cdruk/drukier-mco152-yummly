package drukier.recipe;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import drukier.recipes.Recipe;
import drukier.recipes.RecipeFeedModel;
import drukier.recipes.RecipeProperties;
import drukier.recipes.RecipeService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceTest {

	@Test
	public void testGetAllMonth() throws IOException {
		// given
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://earthquake.usgs.gov")
				.addConverterFactory(GsonConverterFactory.create()).build();

		RecipeService service = retrofit.create(RecipeService.class);

		Call<RecipeFeedModel> call = service.getAllRecipes();

		// when
		Response<RecipeFeedModel> response = call.execute();

		// then
		assertNotNull(response.body());
		List<Recipe> details = response.body().getDetails();
		assertTrue(details.size() > 0);
		RecipeProperties properties = details.get(0).getProperties();
		assertNotNull(properties.getName());
	}

}
