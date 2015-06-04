package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import show.Show;
import show.ShowList;

public class NewShowTabController {

    @FXML private AnchorPane showtabPane;
    @FXML private TextField seasonTxt;
    @FXML private Button seasonDownBtn;
    @FXML private Button episodeDownBtn;
    @FXML private Button episodeUpBtn;
    @FXML private TextField episodeTxt;
    @FXML private Button seasonUpBtn;
    @FXML private TextField showNameTxt;
    @FXML private TextField descriptionTxt;
    @FXML private Button deleteBtn;
    
    private Show show;
    private ShowList showList;
    private VBox parentNode;
	private Region thisNode;
    
	@FXML
	void initialize() {
		
		loadData();
		
		seasonUpBtn.setOnAction(event -> {
			changeSeasonBy(1);	
		});
		seasonDownBtn.setOnAction(event -> {
			changeSeasonBy(-1);	
		});
		episodeUpBtn.setOnAction(event -> {
			changeEpisodeBy(1);
		});
		episodeDownBtn.setOnAction(event -> {
			changeEpisodeBy(-1);
		});
		showNameTxt.textProperty().addListener((observable, oldValue, newValue) -> {
		    show.setName(newValue);
		});
		descriptionTxt.textProperty().addListener((observable, oldValue, newValue) -> {
		    show.setDescription(newValue);
		});
		deleteBtn.setOnAction(event -> {
			showList.removeShow(show);
			parentNode.getChildren().remove(thisNode);
		});
	}

	public void setShow(Show show) {
		this.show = show;
	}

	private void loadData() {
		showNameTxt.setText(show.getName());
		seasonTxt.setText(Integer.toString(show.getSeasonNumber()));
		episodeTxt.setText(Integer.toString(show.getEpisodeNumber()));
		descriptionTxt.setText(show.getDescription());
	}
	
	private void changeSeasonBy(int change) {
		int newNumber = parseInt(seasonTxt)+change;
		show.setSeasonNumber(newNumber);
		seasonTxt.setText(Integer.toString(newNumber));	
	}
	
	private void changeEpisodeBy(int change) {
		int newNumber = parseInt(episodeTxt)+change;
		show.setEpisodeNumber(newNumber);
		episodeTxt.setText(Integer.toString(newNumber));
	}
	
	private int parseInt(TextField tf) {
			int number = 0;
			try {
				number = Integer.parseInt(tf.getText());
			} catch (Exception e) {
			}
			return number;
	}

	public void setParentNode(VBox vbox) { 
		this.parentNode = vbox;
  	}

	public void setShowList(ShowList showList) {
		this.showList = showList;
	}

	public void setThisNode(Region newShowTab) {
		thisNode = newShowTab;
	}
}
