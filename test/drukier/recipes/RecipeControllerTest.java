package drukier.recipes;

import static org.junit.Assert.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;
import org.mockito.Mockito;


import retrofit2.Call;

public class RecipeControllerTest {

	@Test
	public void testSearchRecipe() {

		// given
		RecipeView view = Mockito.mock(RecipeView.class);
		RecipeService service = Mockito.mock(RecipeService.class);
		RecipeController controller = new RecipeController(view, service);
		Call<RecipeFeedModel> call = Mockito.mock(Call.class);
		Mockito.when(service.getAllRecipes(null)).thenReturn(call);
		JTextArea area = Mockito.mock(JTextArea.class);
		Mockito.when(view.getResults()).thenReturn(area);

		// when
		controller.searchRecipe();

		// then
		assertFalse(view.getResults().getText().equals(""));

	}
}
