package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import show.Show;
import show.ShowList;

public class MainGuiController {
//	private Stage stage;
//	private BorderPane layout;
//	private Scene scene;
    
	@FXML private MenuItem menuAbout;
	@FXML private MenuItem menuAdd;
	@FXML private VBox showListBox;
	@FXML private ScrollPane scrollPane;
	@FXML private MenuItem menuSave;
	@FXML private MenuItem menuLoad;
	
    private ShowList showList;
	private BorderPane rootLayout;
	
	@FXML
	void initialize() {
		
		showList = new ShowList();
		
		menuAbout.setOnAction(event -> {
			openMenuStage("about.fxml");
		});
		menuAdd.setOnAction(event -> {
			makeNewShow();
		});
		menuSave.setOnAction(event -> {
			saveShows();
		});
		menuLoad.setOnAction(event -> {
			loadShows();
		});
		
//		showListBox.setFillWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		loadShows();
		
	// add more buttons here
	}
	
	private void openMenuStage(String fxmlFile) {
		 try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(MainGui.class.getResource(fxmlFile));
//	        	Parent root = FXMLLoader.load(getClass(	).getResource("about.fxml"));
	    	Region layout = loader.load();
	        Stage stage = new Stage();
	        Scene scene = new Scene(layout);
	        stage.setScene(scene);
//	            stage.setTitle("My New Stage Title");
//	            stage.setScene(new Scene(root, 450, 450));
	        stage.show();
	
	        //hide this current window (if this is what you want
	        //((Node)(event.getSource())).getScene().getWindow().hide();
	
        } catch (IOException e) {
            e.printStackTrace();
        }
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
			 
			 Region newshowtab = loader.load();
			 controller.setThisNode(newshowtab);
			 
			 /* // set defaults
			 ((TextField) newshowtab.lookup("#showNameTxt")).setText(newShow.getName());
			 ((TextField) newshowtab.lookup("#seasonTxt")).setText(Integer.toString(newShow.getSeasonNumber()));
			 ((TextField) newshowtab.lookup("#episodeTxt")).setText(Integer.toString(newShow.getSeasonNumber()));
			 ((TextField) newshowtab.lookup("#descriptionTxt")).setText(newShow.getDescription());
			 */
			 showListBox.getChildren().add(newshowtab);
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
    
	public void setRootLayout(BorderPane layout) {
		this.rootLayout = layout;
  	}
    
//	  public void setStage(Stage stage) { this.stage = stage; System.out.println("tets1");}
//    public void setScene(Scene scene) { this.scene = scene; System.out.println("tets2");}


}