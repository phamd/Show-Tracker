package gui;

import gui.MainGui;
import gui.NewShowTabController;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import show.Show;
import show.ShowList;

public class MainGuiController {

	@FXML private VBox showListBox;
	@FXML private ScrollPane scrollPane;
	@FXML private MenuItem menuAdd;
	@FXML private MenuItem menuSave;
	@FXML private MenuItem menuLoad;
	@FXML private MenuItem menuAbout;
	@FXML private MenuItem menuInstructions;
	
    private ShowList showList;
	
	@FXML
	void initialize() {
		
		showList = new ShowList();
		
		menuAdd.setOnAction(event -> {
			makeNewShow();
		});
		menuSave.setOnAction(event -> {
			saveShows();
		});
		menuLoad.setOnAction(event -> {
			loadShows();
		});
		
		menuInstructions.setOnAction(event -> {
//			openMenuStage("instructions.fxml");
			System.out.println("No instructions");
		});
		
		menuAbout.setOnAction(event -> {
			openMenuAbout();
		});
		
//		showListBox.setFillWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		loadShows();
	}

	private void openMenuStage(String fxmlFile) {
		 try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(MainGui.class.getResource(fxmlFile));
//	        Parent root = FXMLLoader.load(getClass(	).getResource("about.fxml"));
	    	Region layout = loader.load();
	        Scene scene = new Scene(layout);
	        Stage stage = new Stage();
	        stage.setScene(scene);
//	        stage.setTitle("My New Stage Title");
//	        stage.setScene(new Scene(root, 450, 450));
	        stage.show();
	
//	         hide this current window
//	        ((Node)(event.getSource())).getScene().getWindow().hide();
	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void openMenuAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Welcome to Show Tracker.");
		alert.setContentText("Show Tracker keeps track of numbering information " +
							 "for things such as shows, movies, manga, comics, " +
							 "and books. \n\nThis program was made by: " +
							 "Don Pham & Steven Luu.");
		alert.show();
	}
	
	
	private void makeNewShow() {
		 try {
			 Show newShow = new Show();
			 showList.addShow(newShow);
			 addShowToGui(newShow);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	private void addShowToGui(Show newShow) {
		 try {
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(MainGui.class.getResource("NewShowTab.fxml"));
			 NewShowTabController controller = new NewShowTabController();
			 loader.setController(controller);
			 
			 controller.setShow(newShow);
			 controller.setShowList(showList);
			 controller.setParentNode(showListBox);
			 
			 Region newShowTab = loader.load();
			 controller.setThisNode(newShowTab);
			 
			 /* // set default field text
			 ((TextField) newShowTab.lookup("#showNameTxt")).setText(newShow.getName());
			 ((TextField) newShowTab.lookup("#seasonTxt")).setText(Integer.toString(newShow.getSeasonNumber()));
			 ((TextField) newShowTab.lookup("#episodeTxt")).setText(Integer.toString(newShow.getSeasonNumber()));
			 ((TextField) newShowTab.lookup("#descriptionTxt")).setText(newShow.getDescription());
			 */
			 showListBox.getChildren().add(newShowTab);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	private void saveShows() {
		showList.save();
	}
	
	private void loadShows() {
		showListBox.getChildren().clear(); // clear GUI
		showList.load();

		for (Show show : showList.getShowList()) {
			addShowToGui(show);
		}
	}
	
	public void setShowList(ShowList showList) {
		this.showList = showList;
	}
}