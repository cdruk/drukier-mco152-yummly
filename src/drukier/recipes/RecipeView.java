package drukier.recipes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeView extends JFrame {

	private JTextField searchValue;
	private JTextArea results;
	private JButton search = new JButton("Search");

	public RecipeView() {

		setTitle("Cookbook");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		searchValue = new JTextField();
		results = new JTextArea();

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(0, 3));

		northPanel.add(new JLabel("Search for:", SwingConstants.CENTER));
		northPanel.add(searchValue);
		northPanel.add(search);
		panel.add(northPanel, BorderLayout.NORTH);

		panel.add(results, BorderLayout.CENTER);

		search.addActionListener(this::searchRecipe);

		add(panel);
	}

	private void searchRecipe(ActionEvent e) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.yummly.com/v1/api/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		RecipeService service = retrofit.create(RecipeService.class);

		RecipeController controller = new RecipeController(this, service);

		controller.searchRecipe();
	}

	public JTextArea getResults() {
		return results;
	}

	public void setSearchValue(JTextField searchValue) {
		this.searchValue = searchValue;
	}

	public void setResults(JTextArea results) {
		this.results = results;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	public JButton getSearch() {
		return search;
	}

	public JTextField getSearchValue() {
		return searchValue;
	}

	public static void main(String[] args) {
		new RecipeView().setVisible(true);
	}
}