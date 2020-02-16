package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("sample.fxml"));
		GridPane root = (GridPane)loader.load();


		Controller ctrlr = loader.getController();
		ctrlr.start(primaryStage);

        primaryStage.setTitle("Song Library");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // The way this works is that launch is a method from the applications class
        // then when we call it it normally calls the start function which we're overriding above
        launch(args);

    }
}
