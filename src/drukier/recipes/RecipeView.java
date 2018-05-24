package drukier.recipes;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeView extends JFrame {

	private JTextField searchValue;
	private JEditorPane results = new JEditorPane("text/html", "");
	private JButton search = new JButton("Search");

	public RecipeView() {

		setTitle("Cookbook");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		searchValue = new JTextField();
		results = new JEditorPane();

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(0, 3));

		northPanel.add(new JLabel("Search for:", SwingConstants.CENTER));
		northPanel.add(searchValue);
		northPanel.add(search);
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 2));
		results.setEditable(false);
		results.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		results.addHyperlinkListener(new HyperlinkListener() {
		    public void hyperlinkUpdate(HyperlinkEvent e) {
		        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		            if(Desktop.isDesktopSupported()) {
		                try {
		                    Desktop.getDesktop().browse(e.getURL().toURI());
		                }
		                catch (IOException | URISyntaxException e1) {
		                    e1.printStackTrace();
		                }
		            }
		        }
		    }
		}
		);
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


	public JTextField getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(JTextField searchValue) {
		this.searchValue = searchValue;
	}

	public JEditorPane getResults() {
		return results;
	}

	public void setResults(JEditorPane results) {
		this.results = results;
	}

	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	public static void main(String[] args) {
		new RecipeView().setVisible(true);
	}
}