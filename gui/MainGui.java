package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import show.ShowList;

public class MainGui extends Application {
	
	private class Vector2 {	double x; double y; }
	
	//public BorderPane rootLayout; 
	//public Stage stage;
	//public Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.initStyle(StageStyle.UTILITY); // Hides the titlebar
		primaryStage.setMinWidth(400);
		try {
			startMainGui(primaryStage); // returns rootLayout
			primaryStage.show();
		} catch (IOException e) {
          e.printStackTrace();
     	}
	}
	
	private BorderPane startMainGui(Stage stage) throws IOException {
			// Load the .fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainGui.class.getResource("MainGui.fxml"));
			MainGuiController controller = new MainGuiController(); // http://stackoverflow.com/questions/14187963/passing-parameters-javafx-fxml
			loader.setController(controller);
			
            BorderPane mainGui = (BorderPane) loader.load();
            controller.setRootLayout(mainGui);
			
            // display the scene from the .fxml file
            Scene scene = new Scene(mainGui);
            
            // sets the style for the scene
            scene.getStylesheets().add("gui/MainGui.css");
            
            // sets the stage
            stage.setScene(scene);
            
			// http://stackoverflow.com/a/20477840/1745441
			// https://gist.github.com/jewelsea/2658491
			// http://code.makery.ch/blog/javafx-8-event-handling-examples/
            Node dragthis = mainGui.getTop();
            final Vector2 dragDelta = new Vector2();
            dragthis.setOnMousePressed(event -> {
        		dragDelta.x = stage.getX() - event.getScreenX();
                dragDelta.y = stage.getY() - event.getScreenY();
                scene.setCursor(Cursor.MOVE);
            });
            dragthis.setOnMouseReleased(event -> {
                  scene.setCursor(Cursor.HAND);
            });
            dragthis.setOnMouseDragged(event -> {
              	  stage.setX(event.getScreenX() + dragDelta.x);
              	  stage.setY(event.getScreenY() + dragDelta.y);
            });
            dragthis.setOnMouseEntered(event -> {
                  if (!event.isPrimaryButtonDown()) {
                    scene.setCursor(Cursor.HAND);
                  }
            });
            dragthis.setOnMouseExited(event -> {
                  if (!event.isPrimaryButtonDown()) {
                    scene.setCursor(Cursor.DEFAULT);
                  }
            });
            return mainGui;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
